package com.example.chat.fragmets;

import androidx.fragment.app.Fragment;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

/**
 * ACLARACION:
 * Para kotlin no hay una documentacion oficial de SignalR, solo hay para JavaClient, por eso decidi jugar con java para el uso de conexiones, le dejo la documentacion
 * https://learn.microsoft.com/en-us/aspnet/core/signalr/java-client?view=aspnetcore-7.0
 */
public class SignalRListener {
    private static SignalRListener instance;
    HubConnection hubConnection;

    public SignalRListener(Fragment fragment){
        hubConnection = HubConnectionBuilder.create("https://realtimechatv2.azurewebsites.net/chat").build(); // el servicio puede demorar ya que lo aloje en Azure y solo tenia acceso a Central US

        //await Groups.AddToGroupAsync(Context.ConnectionId, userConnection.Room);
        //hubConnection.on("ReceiveMessage",{})
    }
    public static SignalRListener getInstance(Fragment fragment) {
        if (instance == null)
            instance = new SignalRListener(fragment);
        return instance;
    }
}
