package com.example.draw_menu

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.*

import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.example.draw_menu.databinding.ActivityScrollingBinding


class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val array: ArrayList<Donation_details> =
            arrayListOf(
                Donation_details(
                    "Ram prasad",
                    "Farmland got infertile due to severe drought",
                    R.drawable.sampleimage_2
                ),
                Donation_details(
                    "Rani ",
                    "Youngest son suffering from cancer",
                    R.drawable.smapleimage_1
                ),
                Donation_details("tom ", "factory closed", R.drawable.sample_3)
            )
        val donationItemAdapter: GenericAdapter<Donation_details> =
            object : GenericAdapter<Donation_details>(this, array) {
                override val layoutResourceId: Int
                    get() = R.layout.item_donation

                override fun onDataBind(
                    model: Donation_details,
                    position: Int,
                    holder: RecyclerView.ViewHolder?
                ) {
                    val nameTextView = holder?.itemView?.findViewById<TextView>(R.id.name)
                    val donateTextView = holder?.itemView?.findViewById<TextView>(R.id.donate)
                    val imageView = holder?.itemView?.findViewById<ImageView>(R.id.profile_image)
                    nameTextView?.text = "NAME :" + model.name

                    imageView?.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@ScrollingActivity,
                            model.imageResourse
                        )
                    )

                    val courseTextView = holder?.itemView?.findViewById<TextView>(R.id.course)
                    courseTextView?.text = "CAUSE :" + model.couse
                    donateTextView?.setOnClickListener {
                        binding.scrolllayout.itemrecyclerview?.visibility = View.GONE
                        binding.scrolllayout.details?.root?.visibility = View.VISIBLE
                        binding.subtitle.setText("Transaction details")
                        binding.scrolllayout.applytextview?.setText("Pay now")
                        binding.toolbar.setExpanded(true)
                        binding.searchEdittext.setHint("       DONATE NOW      ")
                        binding.searchEdittext.text.clear()
                    }


                }

            }

        binding.scrolllayout.itemrecyclerview?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = donationItemAdapter

        }

        val languages = resources.getStringArray(R.array.currency)
        val spinner = findViewById<Spinner>(R.id.currencyspinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, languages
            )
            spinner.adapter = adapter
        }


    }

    override fun onBackPressed() {
        if (binding.scrolllayout.itemrecyclerview?.visibility == View.GONE) {
            binding.scrolllayout.itemrecyclerview?.visibility = View.VISIBLE
            binding.scrolllayout.details?.root?.visibility = View.GONE
            binding.subtitle.setText("Lorem ipsum")
            binding.scrolllayout.applytextview?.setText(getString(R.string.apply_for_a_n_fundraiser))
            binding.searchEdittext.setHint(getString(R.string.search_for_a_fundraiser))
            binding.searchEdittext.text.clear()
        } else
            super.onBackPressed()
    }
}

    /* override fun onCreateOptionsMenu(menu : Menu) : Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true }

   override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
   }
} */