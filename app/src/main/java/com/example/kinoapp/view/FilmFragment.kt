package com.example.kinoapp.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.kinoapp.App
import com.example.kinoapp.R
import com.example.kinoapp.adapters.ItemFilmAdapter
import com.example.kinoapp.interfaces.Contract
import com.example.kinoapp.model.datamodel.Film
import com.example.kinoapp.model.datamodel.ListItem
import com.example.kinoapp.presenter.FilmPresenter
import dagger.internal.DaggerCollections
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class FilmFragment : Fragment(), ItemFilmAdapter.ItemClickInterface,
    Contract.View {

    private lateinit var adapter: ItemFilmAdapter
    private var listForSave: List<ListItem> = ArrayList()
    private var listFilm: List<Film> = ArrayList()

    //Dagger2
    @Inject
    lateinit var dPresenter: FilmPresenter

    companion object {
        const val HEADER = 1003
        const val GENRES = 1001
        const val LIST_KEY = "LIST_KEY"
        const val FILM = "FILM"
        const val PRESENTER = "PRESENTER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        dPresenter = App.appComponent.getPresenter()
        dPresenter.setView(this)
        Log.d("dP", "onCreate dP$dPresenter")
        if (savedInstanceState != null) {
            listForSave = savedInstanceState.getSerializable(LIST_KEY) as List<ListItem>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecycler()
        if (savedInstanceState != null) {
            listForSave = savedInstanceState.getSerializable(LIST_KEY) as List<ListItem>
            updateAdapter(listForSave)
        } else {
            dPresenter.callApi()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(LIST_KEY, ArrayList<ListItem>(listForSave))
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (savedInstanceState != null) {
            listForSave = savedInstanceState.getSerializable(LIST_KEY) as List<ListItem>
        }
    }
    override fun onClickGenres(genres: String,state: Boolean) {
        dPresenter.sendSelected(genres,state)
    }

    override fun onClicked(film: Film) {
        val args = Bundle()
        args.putString("name", film.name)
        args.putString("local_name", film.localized_name)
        args.putString("film_cover", film.image_url)
        args.putString("year", film.year.toString())
        args.putString("film_description", film.description)
        args.putString("rating", film.rating.toString())
        args.putSerializable("film", film)
        findNavController().navigate(R.id.action_mainFragment_to_filmInfoFragment, args)
    }

    private fun prepareRecycler() {
        val glm = GridLayoutManager(context, 2)
        glm.setSpanSizeLookup(object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    HEADER -> 2
                    GENRES -> 2
                    else -> 1
                }
            }
        })
        recycler_view.layoutManager = glm
        adapter = context?.let { ItemFilmAdapter(it, this) }!!
        recycler_view.adapter = adapter
        recycler_view.setHasFixedSize(true)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun updateAdapter(list: List<ListItem>) {
        listForSave = list
        adapter.update(list)
        adapter.notifyDataSetChanged()
    }
}
