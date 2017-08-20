package ClickableRedditLinks;


import com.comphenix.protocol.PacketType;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.plugin.Plugin;


public class ReplaceAllChat {

    HelperClass hc = new HelperClass();

    public void init(Plugin pl) {

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(pl, PacketType.Play.Server.CHAT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                StructureModifier<WrappedChatComponent> chatComponents = packet.getChatComponents();
                WrappedChatComponent msg = chatComponents.read(0);
                String message = msg.getJson();

                message = hc.FilterMessage(message, true);

             /*   try {
                    if (message.contains("/r/") && !message.contains("reddit.com/r/")) {
                        String subreddit = hc.FindSubString(message, "r");
                        message = message.replace("/r/" + subreddit, hc.link);
                        message = message.replaceAll("ToBeReplacedWithStuff", "/r/" + subreddit);
                    }
                    if (message.contains("/u/") && !message.contains("reddit.com/u/")) {
                        String user = hc.FindSubString(message, "u");
                        message = message.replace("/u/" + user, hc.link);
                        message = message.replaceAll("ToBeReplacedWithStuff", "/u/" + user);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("[ClickableRedditLinks] An error occurred while trying to get the subreddit/user");
                    return;
                }*/

                try {
                    msg.setJson(message);
                    chatComponents.write(0, msg);
                } catch (Exception e) {
                    System.out.println("[ClickableRedditLinks] An error occurred while trying to convert back to json chat");
                    return;
                }
            }

            });

    }
}