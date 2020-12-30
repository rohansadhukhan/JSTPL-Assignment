package com.example.jstpl_assignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.jstpl_assignment.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginVerification.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginVerification(
    private val number: String
) : Fragment() {

    private lateinit var mobile_number: TextView
    private lateinit var verify_otp_button: Button
    private lateinit var progress_bar: ProgressBar
    private lateinit var listener: OnVerifyOTP
    private lateinit var inp1: EditText
    private lateinit var inp2: EditText
    private lateinit var inp3: EditText
    private lateinit var inp4: EditText
    private lateinit var inp5: EditText
    private lateinit var inp6: EditText


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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_verification, container, false)

        mobile_number = view.findViewById(R.id.display_mobile_number)
        verify_otp_button = view.findViewById(R.id.verify_otp_button)
        progress_bar = view.findViewById(R.id.progress_bar2)

        inp1 = view.findViewById(R.id.input_code_1)
        inp2 = view.findViewById(R.id.input_code_2)
        inp3 = view.findViewById(R.id.input_code_3)
        inp4 = view.findViewById(R.id.input_code_4)
        inp5 = view.findViewById(R.id.input_code_5)
        inp6 = view.findViewById(R.id.input_code_6)

        updatePhoneNumber("+91-$number")

        verify_otp_button.setOnClickListener {
            var otp: String
            if (checkOTP()) {
                otp = decodeOTP()
                listener.onVerifyOTP(otp)
            }
        }
        return view
    }

    private fun decodeOTP(): String {
        var otp: String = ""
        otp += inp1.text.toString()
        otp += inp2.text.toString()
        otp += inp3.text.toString()
        otp += inp4.text.toString()
        otp += inp5.text.toString()
        otp += inp6.text.toString()
        return otp
    }

    private fun checkOTP(): Boolean {
        if (inp1.text.isEmpty() ||
            inp2.text.isEmpty() ||
            inp3.text.isEmpty() ||
            inp4.text.isEmpty() ||
            inp5.text.isEmpty() ||
            inp6.text.isEmpty()
        ) return false
        return true
    }


    fun updatePhoneNumber(number: String) {
        mobile_number.text = number
    }

    interface OnVerifyOTP {
        fun onVerifyOTP(otp: String)
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment LoginVerification.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            LoginVerification().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}