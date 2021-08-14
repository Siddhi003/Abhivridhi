package com.example.draw_menu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class Login : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,container : ViewGroup?, 
        savedInstancesState : Bundle?): View ? {
            return inflater.inflate(R.layout.fragment_login,container,false)
    
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn: TextView=view.findViewById(R.id.textviewSignUp)
        btn.setOnClickListener {
            val fragment = RegisterFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container,fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}

