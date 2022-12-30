package com.example.chat.fragmets

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chat.R
import com.example.chat.databinding.FragmentChatBinding

private const val STRING_USUARIO = "com.example.chat.fragments.chatfragment.usuario"
private const val STRING_SALA = "com.example.chat.fragments.chatfragment.sala"
class ChatFragment : Fragment() {
    private var user:String? = null
    private var sala:String? = null

    var listener : ChatFragment.MessageListener? = null

    private var _binding : FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            user = it.getString(STRING_USUARIO)
            sala = it.getString(STRING_SALA)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ChatFragment.MessageListener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatBinding.inflate(inflater,container,false)

        binding.btnDesconectar.setOnClickListener {
            listener?.let {
                it.onSendMessageChatToLobby("")
            }
        }

        return binding.root
    }
    private fun SalirSala(){

    }

    /**
     * con esto extraigo los parametros traidos desde LobbyFragment para iniciar sesion en una sala del chat que se enviara a SignalR
     */
    companion object {

        fun newInstance(usuario: String,sala:String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(STRING_USUARIO, usuario) //requerido
                    putString(STRING_SALA, sala) //requerido
                }
            }
        }


    /**
     * esta interface es de para comunicarnos desde este fragmento a LobbyFragment mediante MainActibity la cual enviara el error de la excepcion
     */
    interface MessageListener{
        fun onSendMessageChatToLobby(error:String)
    }
}