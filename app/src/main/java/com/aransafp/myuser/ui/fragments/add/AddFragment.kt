package com.aransafp.myuser.ui.fragments.add

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
import com.aransafp.myuser.R
import com.aransafp.myuser.data.entity.User
import com.aransafp.myuser.databinding.FragmentAddBinding
import com.aransafp.myuser.ui.UserViewModel
import com.aransafp.myuser.utils.ViewModelFactory

class AddFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        binding.btnSubmit.setOnClickListener {
            insertDataToDatabase()
        }

    }

    private fun insertDataToDatabase() {

        with(binding) {
            val firstName = edtFirstName.text.toString()
            val lastName = edtLastName.text.toString()
            val age = edtAge.text

            if (inputCheck(firstName, lastName, age)) {

                val user = User(0, firstName, lastName, age.toString().toInt())
                //add to database
                userViewModel.addUser(user)
                Toast.makeText(requireContext(), "Succesfully Added!", Toast.LENGTH_SHORT).show()

                //Navigate back
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            } else {
                Toast.makeText(requireContext(), "Failed Added!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}