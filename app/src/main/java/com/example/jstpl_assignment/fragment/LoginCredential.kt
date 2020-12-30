package com.example.jstpl_assignment.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.jstpl_assignment.R
import java.lang.Exception
import java.lang.RuntimeException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginCredential.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginCredential : Fragment() {

    lateinit var listener : OnRequestOTP

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login_credential, container, false)

        var input_number: TextView = view.findViewById(R.id.mobile_number)
        var get_otp_button: Button = view.findViewById(R.id.get_otp_button)
        var progress_bar: ProgressBar = view.findViewById(R.id.progress_bar1)

        get_otp_button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var number : String? = input_number.text.toString()
                if(number?.length == 10) {
                    listener.onRequestOTP(number)
                }
            }

        })

        return view
    }

    interface OnRequestOTP {
        fun onRequestOTP(number: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnRequestOTP) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement FragmentEvent")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginCredential.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginCredential().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}