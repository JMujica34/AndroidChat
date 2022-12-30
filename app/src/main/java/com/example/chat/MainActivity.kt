package com.example.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.chat.databinding.ActivityMainBinding
import com.example.chat.fragmets.ChatFragment
import com.example.chat.fragmets.LobbyFragment

class MainActivity : AppCompatActivity(), LobbyFragment.MessageListener, ChatFragment.MessageListener {

    //var conexion = false;
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarFragmento(LobbyFragment())
    }

    /**
     * Esta funcion carga la plantilla general de cada fragmento, remplazando el actual
     *
     * @param fragmento es el fragmento que se va a cagar como principal
     */
    fun cargarFragmento(fragmento:Fragment){
        //val fragmentTransaction = supportFragmentManager.beginTransaction().replace(binding.layoutMainContainer.id, {conexion? lobbyFragment : chatFragment}) esto no funciono
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.layoutMainContainer.id,fragmento)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**
     * Esta funcion es un escuchador llamado desde LobbyFragment para enviar datos del fragmento Lobby a Chat e invocar a la carga del fragmento ChatFragment
     * @param usuario , es el parametro necesitado por el servicio SignalR para darle entidad al enviar mensaje
     * @param sala , es el parametro necesitado por el servicio SignalR para crear una nueva sala y agrupar usuarios en la misma
     */
    override fun onSendMessageLobbyToChat(usuario: String, sala: String) {
        val chatFragment = ChatFragment.newInstance(usuario,sala)
        cargarFragmento(chatFragment)
    }

    /**
     * Esta funcion es un escuchador llamado desde ChatFragment para enviar datos de error del fragmento en caso de excepciones al conectar con SignalR e invica a la carga de fragmento LobbyFragment
     * @param error es la notificacion de algun error enviado desde SignalR
     */
    override fun onSendMessageChatToLobby(error: String) {
        val lobbyFragment = LobbyFragment.newInstance(error)
        cargarFragmento(lobbyFragment)
    }


}