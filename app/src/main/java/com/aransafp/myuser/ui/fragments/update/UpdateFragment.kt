package com.aransafp.myuser.ui.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aransafp.myuser.R
import com.aransafp.myuser.ui.UserViewModel
import com.aransafp.myuser.data.entity.User
import com.aransafp.myuser.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.edtFirstName

        with(binding) {
            edtFirstName.setText(args.currentUser.firstName)
            edtLastName.setText(args.currentUser.lastName)
            edtAge.setText(args.currentUser.age.toString())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnSubmit.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser() {
        with(binding) {
            val firstName = binding.edtFirstName.text.toString()
            val lastName = binding.edtLastName.text.toString()
            val age = binding.edtAge.text

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