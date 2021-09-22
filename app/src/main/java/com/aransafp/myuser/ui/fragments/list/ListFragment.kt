package com.aransafp.myuser.ui.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aransafp.myuser.R
import com.aransafp.myuser.databinding.FragmentListBinding
import com.aransafp.myuser.ui.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recycler view
        val adapter = ListAdapter()
        with(binding) {
            rvUser.adapter = adapter
            rvUser.layoutManager = LinearLayoutManager(requireContext())
        }

        //viewModel
        userViewModel.readAllData.observe(viewLifecycleOwner, { users ->
            adapter.setData(users)
        })

        swipeToDelete()

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

    }

    private fun swipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val user = (viewHolder as ListAdapter.ViewHolder).getUser
                userViewModel.deleteUser(user)
                Toast.makeText(requireContext(), "Deleted successfully", Toast.LENGTH_SHORT).show()
            }

        })

        itemTouchHelper.attachToRecyclerView(binding.rvUser)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}