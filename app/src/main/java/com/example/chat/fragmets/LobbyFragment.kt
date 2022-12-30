package com.example.chat.fragmets

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chat.R
import com.example.chat.databinding.FragmentLobbyBinding

private const val STRING_ERROR = "com.example.chat.fragments.lobbyfragment.error"
class LobbyFragment : Fragment() {


    var listener : MessageListener? = null
    private var _binding : FragmentLobbyBinding? = null
    private val binding get() = _binding!!
    private var error:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            error = it.getString(STRING_ERROR)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MessageListener
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLobbyBinding.inflate(inflater,container,false)

        binding.btnIngresar.setOnClickListener {
            validarIdentidad()
        }
        binding.tvError.setText(this.error)
        return binding.root
    }

    private fun validarIdentidad(){
        if (!binding.etNombre.text.toString().isNullOrEmpty() && !binding.etSala.text.toString().isNullOrEmpty()){
            Toast.makeText(context,"Bienvenido a la sala ${binding.etSala.text.toString()}",Toast.LENGTH_LONG).show()
            sendMessage()
        }else{
            Toast.makeText(context,"El usuario y sala son requeridos",Toast.LENGTH_LONG).show()
        }
    }

    private fun sendMessage(){
        listener?.let {
            it.onSendMessageLobbyToChat(binding.etNombre.text.toString(),binding.etSala.text.toString())
        }
    }

    /**
     * con esto extraigo los parametros traidos desde ChatFragment para indicar si hubo un error al inciar sesion y en medio del funcionamiento del chat enviado x signalR
     */

    companion object {

        fun newInstance(error: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(STRING_ERROR, error)
                }
            }
    }

    /**
     * esta interface es de para comunicarnos desde este fragmento a ChatFragment mediante MainActibity la cual enviara el usuario y la sala para el incio de sesion
     */
    interface MessageListener{
        fun onSendMessageLobbyToChat(usuario:String,sala:String)
    }

}