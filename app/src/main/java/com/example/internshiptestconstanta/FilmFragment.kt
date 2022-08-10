package com.example.internshiptestconstanta

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.internshiptestconstanta.adapter.FilmItemAdapter
import com.example.internshiptestconstanta.databinding.FragmentFilmBinding

class FilmFragment : Fragment() {

    private val sharedViewModel: FilmViewModel by activityViewModels()
    private lateinit var binding: FragmentFilmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentFilmBinding.inflate(inflater, container, false)
        val positiveButtonClick = { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        binding = fragmentBinding

        binding.lifecycleOwner = this

        binding.viewModel = sharedViewModel

        binding.linearRecyclerView.adapter = FilmItemAdapter { film ->
            sharedViewModel.updateCurrentFilm(film)
            AlertDialog.Builder(context)
                .setMessage("Фильм ${film.title} был нажат")
                .setPositiveButton("OK", DialogInterface.OnClickListener(positiveButtonClick))
                .create()
                .show()
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filmFragment = this
    }
}