package com.aransafp.myuser.ui.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aransafp.myuser.R
import com.aransafp.myuser.data.entity.User
import com.aransafp.myuser.databinding.FragmentUpdateBinding
import com.aransafp.myuser.ui.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            edtFirstName.setText(args.currentUser.firstName)
            edtLastName.setText(args.currentUser.lastName)
            edtAge.setText(args.currentUser.age.toString())
        }

        binding.btnSubmit.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser() {
        with(binding) {
            val firstName = edtFirstName.text.toString()
            val lastName = edtLastName.text.toString()
            val age = edtAge.text

            if (inputCheck(firstName, lastName, age)) {
                //update User
                val user = User(args.currentUser.id, firstName, lastName, age.toString().toInt())

                //update to room
                userViewModel.updateUser(user)
                Toast.makeText(requireContext(), "Succesfully Added!", Toast.LENGTH_SHORT).show()

                //navigate back
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)

            } else {
                Toast.makeText(requireContext(), "fail update", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}