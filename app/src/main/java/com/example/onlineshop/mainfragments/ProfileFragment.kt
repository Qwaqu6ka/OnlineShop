package com.example.onlineshop.mainfragments

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.onlineshop.BaseFragment
import com.example.onlineshop.R
import com.example.onlineshop.SignInActivity
import com.example.onlineshop.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val chooseImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.profilePhoto.setImageURI(it)
        }

    private val checkPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            val granted = (it == true)
            when {
                granted -> chooseImageLauncher.launch("image/*")
                !shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> showPermissionDeniedDialog()
                else -> showRationaleDialog()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changePhotoButton.setOnClickListener {
            checkPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        binding.logOutTab.setOnClickListener {
            startActivity(Intent(context, SignInActivity::class.java))
            activity?.finish()
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.permission_required)
            .setMessage(R.string.permission_denied_dialog_message)
            .setPositiveButton(R.string.settings) { _, _ ->
                val uri = Uri.fromParts("package", requireActivity().packageName, null)
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri)
                ContextCompat.startActivity(requireContext(), intent, null)
            }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .show()
    }

    private fun showRationaleDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.permission_required)
            .setMessage(R.string.storage_permission_rationale)
            .setPositiveButton(R.string.allow) { _, _ ->
                checkPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .show()
    }
}