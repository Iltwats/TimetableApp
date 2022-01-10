package com.example.timetablebphc.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.timetablebphc.R
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment : Fragment() {

    private lateinit var moreViewModel: MoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moreViewModel = ViewModelProvider(this).get(MoreViewModel::class.java)
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sing_out_button.setOnClickListener {
            val builder = context?.let { AlertDialog.Builder(it) }
            if (builder != null) {
                builder.setMessage("Are you sure you want to sign out?").setCancelable(false)
                    .setPositiveButton("Yes") { _, _ ->
                        moreViewModel.signOut()
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.action_navigation_more_to_signin_activity)
                    }.setNegativeButton("No") { dialog, _ ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                builder.create().show()
            }
        }
    }

}