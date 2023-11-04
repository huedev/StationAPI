package net.modificationstation.stationapi.api.packet;

import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.modificationstation.stationapi.api.registry.IdentifiablePacketRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.impl.packet.IdentifiablePacketImpl;

public interface IdentifiablePacket {

    int PACKET_ID = 254;

    static Factory create(Identifier id, boolean receivableOnClient, boolean receivableOnServer, Factory factory) {
        if (IdentifiablePacketRegistry.INSTANCE.containsId(id))
            throw new IllegalArgumentException("Duplicate packet id:" + id);
        Factory ret = Registry.register(IdentifiablePacketRegistry.INSTANCE, id, factory);
        if (receivableOnClient) IdentifiablePacketImpl.SERVER_TO_CLIENT_PACKETS.add(id);
        if (receivableOnServer) IdentifiablePacketImpl.CLIENT_TO_SERVER_PACKETS.add(id);
        return ret;
    }

    static void setHandler(Identifier packetId, NetworkHandler handler) {
        NetworkHandler previous = IdentifiablePacketImpl.HANDLERS.put(packetId, handler);
        if (previous != null)
            throw new IllegalArgumentException("Duplicate handler for packet id \"" + packetId + "\"! Previous: \"" + previous.getClass().getName() + "\", new: \"" + handler.getClass().getName() + "\"");
    }

    static Packet create(Identifier id) {
        Factory factory = IdentifiablePacketRegistry.INSTANCE.get(id);
        return factory == null ? null : factory.create();
    }

    Identifier getId();

    @FunctionalInterface
    interface Factory {
        Packet create();
    }
}
