package me.coderfrish.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import xyz.ankairmc.ankair.network.PacketBuffer;

import java.util.*;
import java.util.function.BiConsumer;

public class DataTypeTest {
    @Test
    public void test() {
//        ByteBuf buffer = Unpooled.buffer();
//        List<Integer> list = Arrays.asList(1, 20, 30);
//
//        writeCollection(buffer, list, DataTypeTest::writeVarInt);
//        System.out.println(readCollection(buffer, DataTypeTest::readVarInt).toString());
    }

    public <C> ByteBuf writeCollection(ByteBuf buf, Collection<C> collection, BiConsumer<ByteBuf, C> runnable) {
        writeVarInt(buf, collection.size());

        for (C c : collection) {
            runnable.accept(buf, c);
        }

        return buf;
    }

    public <C> Collection<C> readCollection(ByteBuf buf, java.util.function.Function<ByteBuf, C> runnable) {
        Collection<C> collection = new ArrayList<>(Collections.emptySet());
        int length = readVarInt(buf);

        for (int i = 0; i < length; i++) {
            collection.add(runnable.apply(buf));
        }

        return collection;
    }

    public record Position(int x, int y, int z) {
        public long toLong() {
            return (((long) x & (long) 67108863) << 38) | (((long) y & (long) 4095)) | (((long) z & (long) 67108863) << 12);
        }

        public static Position toPosition(long value) {
            return new Position((int) (value >> 38), (int) ((value << 52) >> 52), (int) ((value << 26) >> 38));
        }
    }

    public static long readVarLong(ByteBuf buf) {
        long l = 0L;
        int i = 0;

        byte b;
        do {
            b = buf.readByte();
            l |= (long)(b & 127) << i++ * 7;
            if (i > 10) {
                throw new RuntimeException("VarLong too big");
            }
        } while ((b & 128) == 128);

        return l;
    }

    public static ByteBuf writeVarLong(ByteBuf buf, long l) {
        while ((l & -128L) != 0L) {
            buf.writeByte((int)(l & 127L) | 128);
            l >>>= 7;
        }

        buf.writeByte((int)l);
        return buf;
    }

    public static int readVarInt(ByteBuf buf) {
        int i = 0;
        int j = 0;

        byte b;
        do {
            b = buf.readByte();
            i |= (b & 127) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((b & 128) == 128);

        return i;
    }

    public static ByteBuf writeVarInt(ByteBuf buf, int i) {
        if ((i & (0xFFFFFFFF << 7)) == 0) {
            buf.writeByte(i);
        } else if ((i & (0xFFFFFFFF << 14)) == 0) {
            int w = (i & 0x7F | 0x80) << 8 | (i >>> 7);
            buf.writeShort(w);
        } else {
            while ((i & -128) != 0) {
                buf.writeByte(i & 127 | 128);
                i >>>= 7;
            }

            buf.writeByte(i);
        }
        return buf;
    }

    public static ByteBuf writePosition(ByteBuf buf, Position position) {
        return buf.writeLong(position.toLong());
    }

    public static Position readPosition(ByteBuf buf) {
        return Position.toPosition(buf.readLong());
    }
}
