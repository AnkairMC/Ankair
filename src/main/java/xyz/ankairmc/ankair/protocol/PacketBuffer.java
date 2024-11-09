package xyz.ankairmc.ankair.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ByteProcessor;
import xyz.ankairmc.ankair.core.Position;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.BiConsumer;

/**
 * @author Frish2021
 * @see io.netty.buffer.ByteBuf
 */
@SuppressWarnings("all")
public class PacketBuffer extends ByteBuf {
    private final ByteBuf data;

    public PacketBuffer(ByteBuf data) {
        this.data = data;
    }

    @Override
    public int capacity() {
        return data.capacity();
    }

    @Override
    public ByteBuf capacity(int i) {
        return data.capacity(i);
    }

    @Override
    public int maxCapacity() {
        return data.maxCapacity();
    }

    @Override
    public ByteBufAllocator alloc() {
        return data.alloc();
    }

    @Override
    @Deprecated
    public ByteOrder order() {
        return data.order();
    }

    @Override
    @Deprecated
    public ByteBuf order(ByteOrder byteOrder) {
        return data.order(byteOrder);
    }

    @Override
    public ByteBuf unwrap() {
        return data.unwrap();
    }

    @Override
    public boolean isDirect() {
        return data.isDirect();
    }

    @Override
    public boolean isReadOnly() {
        return data.isReadOnly();
    }

    @Override
    public ByteBuf asReadOnly() {
        return data.asReadOnly();
    }

    @Override
    public int readerIndex() {
        return data.readerIndex();
    }

    @Override
    public ByteBuf readerIndex(int i) {
        return data.readerIndex(i);
    }

    @Override
    public int writerIndex() {
        return data.writerIndex();
    }

    @Override
    public ByteBuf writerIndex(int i) {
        return data.writerIndex(i);
    }

    @Override
    public ByteBuf setIndex(int i, int i1) {
        return data.setIndex(i, i1);
    }

    @Override
    public int readableBytes() {
        return data.readableBytes();
    }

    @Override
    public int writableBytes() {
        return data.writableBytes();
    }

    @Override
    public int maxWritableBytes() {
        return data.maxWritableBytes();
    }

    @Override
    public boolean isReadable() {
        return data.isReadable();
    }

    @Override
    public boolean isReadable(int i) {
        return data.isReadable(i);
    }

    @Override
    public boolean isWritable() {
        return data.isWritable();
    }

    @Override
    public boolean isWritable(int i) {
        return data.isWritable(i);
    }

    @Override
    public ByteBuf clear() {
        return data.clear();
    }

    @Override
    public ByteBuf markReaderIndex() {
        return data.markReaderIndex();
    }

    @Override
    public ByteBuf resetReaderIndex() {
        return data.resetReaderIndex();
    }

    @Override
    public ByteBuf markWriterIndex() {
        return data.markWriterIndex();
    }

    @Override
    public ByteBuf resetWriterIndex() {
        return data.resetWriterIndex();
    }

    @Override
    public ByteBuf discardReadBytes() {
        return data.discardReadBytes();
    }

    @Override
    public ByteBuf discardSomeReadBytes() {
        return data.discardSomeReadBytes();
    }

    @Override
    public ByteBuf ensureWritable(int i) {
        return data.ensureWritable(i);
    }

    @Override
    public int ensureWritable(int i, boolean b) {
        return data.ensureWritable(i, b);
    }

    @Override
    public boolean getBoolean(int i) {
        return data.getBoolean(i);
    }

    @Override
    public byte getByte(int i) {
        return data.getByte(i);
    }

    @Override
    public short getUnsignedByte(int i) {
        return data.getUnsignedByte(i);
    }

    @Override
    public short getShort(int i) {
        return data.getShort(i);
    }

    @Override
    public short getShortLE(int i) {
        return data.getShortLE(i);
    }

    @Override
    public int getUnsignedShort(int i) {
        return data.getUnsignedShort(i);
    }

    @Override
    public int getUnsignedShortLE(int i) {
        return data.getUnsignedShortLE(i);
    }

    @Override
    public int getMedium(int i) {
        return data.getMedium(i);
    }

    @Override
    public int getMediumLE(int i) {
        return data.getMediumLE(i);
    }

    @Override
    public int getUnsignedMedium(int i) {
        return data.getUnsignedMedium(i);
    }

    @Override
    public int getUnsignedMediumLE(int i) {
        return data.getUnsignedMediumLE(i);
    }

    @Override
    public int getInt(int i) {
        return data.getInt(i);
    }

    @Override
    public int getIntLE(int i) {
        return data.getIntLE(i);
    }

    @Override
    public long getUnsignedInt(int i) {
        return data.getUnsignedInt(i);
    }

    @Override
    public long getUnsignedIntLE(int i) {
        return data.getUnsignedIntLE(i);
    }

    @Override
    public long getLong(int i) {
        return data.getLong(i);
    }

    @Override
    public long getLongLE(int i) {
        return data.getLongLE(i);
    }

    @Override
    public char getChar(int i) {
        return data.getChar(i);
    }

    @Override
    public float getFloat(int i) {
        return data.getFloat(i);
    }

    @Override
    public double getDouble(int i) {
        return data.getDouble(i);
    }

    @Override
    public ByteBuf getBytes(int i, ByteBuf byteBuf) {
        return data.getBytes(i, byteBuf);
    }

    @Override
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i1) {
        return data.getBytes(i, byteBuf, i1);
    }

    @Override
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i1, int i2) {
        return data.getBytes(i, byteBuf, i1, i2);
    }

    @Override
    public ByteBuf getBytes(int i, byte[] bytes) {
        return data.getBytes(i, bytes);
    }

    @Override
    public ByteBuf getBytes(int i, byte[] bytes, int i1, int i2) {
        return data.getBytes(i, bytes, i1, i2);
    }

    @Override
    public ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        return data.getBytes(i, byteBuffer);
    }

    @Override
    public ByteBuf getBytes(int i, OutputStream outputStream, int i1) throws IOException {
        return data.getBytes(i, outputStream, i1);
    }

    @Override
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i1) throws IOException {
        return data.getBytes(i, gatheringByteChannel, i1);
    }

    @Override
    public int getBytes(int i, FileChannel fileChannel, long l, int i1) throws IOException {
        return data.getBytes(i, fileChannel, l, i1);
    }

    @Override
    public CharSequence getCharSequence(int i, int i1, Charset charset) {
        return data.getCharSequence(i, i1, charset);
    }

    @Override
    public ByteBuf setBoolean(int i, boolean b) {
        return data.setBoolean(i, b);
    }

    @Override
    public ByteBuf setByte(int i, int i1) {
        return data.setByte(i, i1);
    }

    @Override
    public ByteBuf setShort(int i, int i1) {
        return data.setShort(i, i1);
    }

    @Override
    public ByteBuf setShortLE(int i, int i1) {
        return data.setShortLE(i, i1);
    }

    @Override
    public ByteBuf setMedium(int i, int i1) {
        return data.setMedium(i, i1);
    }

    @Override
    public ByteBuf setMediumLE(int i, int i1) {
        return data.setMediumLE(i, i1);
    }

    @Override
    public ByteBuf setInt(int i, int i1) {
        return data.setInt(i, i1);
    }

    @Override
    public ByteBuf setIntLE(int i, int i1) {
        return data.setIntLE(i, i1);
    }

    @Override
    public ByteBuf setLong(int i, long l) {
        return data.setLong(i, l);
    }

    @Override
    public ByteBuf setLongLE(int i, long l) {
        return data.setLongLE(i, l);
    }

    @Override
    public ByteBuf setChar(int i, int i1) {
        return data.setChar(i, i1);
    }

    @Override
    public ByteBuf setFloat(int i, float v) {
        return data.setFloat(i, v);
    }

    @Override
    public ByteBuf setDouble(int i, double v) {
        return data.setDouble(i, v);
    }

    @Override
    public ByteBuf setBytes(int i, ByteBuf byteBuf) {
        return data.setBytes(i, byteBuf);
    }

    @Override
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i1) {
        return data.setBytes(i, byteBuf, i1);
    }

    @Override
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i1, int i2) {
        return data.setBytes(i, byteBuf, i1, i2);
    }

    @Override
    public ByteBuf setBytes(int i, byte[] bytes) {
        return data.setBytes(i, bytes);
    }

    @Override
    public ByteBuf setBytes(int i, byte[] bytes, int i1, int i2) {
        return data.setBytes(i, bytes, i1, i2);
    }

    @Override
    public ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        return data.setBytes(i, byteBuffer);
    }

    @Override
    public int setBytes(int i, InputStream inputStream, int i1) throws IOException {
        return data.setBytes(i, inputStream, i1);
    }

    @Override
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i1) throws IOException {
        return data.setBytes(i, scatteringByteChannel, i1);
    }

    @Override
    public int setBytes(int i, FileChannel fileChannel, long l, int i1) throws IOException {
        return data.setBytes(i, fileChannel, l, i1);
    }

    @Override
    public ByteBuf setZero(int i, int i1) {
        return data.setZero(i, i1);
    }

    @Override
    public int setCharSequence(int i, CharSequence charSequence, Charset charset) {
        return data.setCharSequence(i, charSequence, charset);
    }

    @Override
    public boolean readBoolean() {
        return data.readBoolean();
    }

    @Override
    public byte readByte() {
        return data.readByte();
    }

    @Override
    public short readUnsignedByte() {
        return data.readUnsignedByte();
    }

    @Override
    public short readShort() {
        return data.readShort();
    }

    @Override
    public short readShortLE() {
        return data.readShortLE();
    }

    @Override
    public int readUnsignedShort() {
        return data.readUnsignedShort();
    }

    @Override
    public int readUnsignedShortLE() {
        return data.readUnsignedShortLE();
    }

    @Override
    public int readMedium() {
        return data.readMedium();
    }

    @Override
    public int readMediumLE() {
        return data.readMediumLE();
    }

    @Override
    public int readUnsignedMedium() {
        return data.readUnsignedMedium();
    }

    @Override
    public int readUnsignedMediumLE() {
        return data.readUnsignedMediumLE();
    }

    @Override
    public int readInt() {
        return data.readInt();
    }

    @Override
    public int readIntLE() {
        return data.readIntLE();
    }

    @Override
    public long readUnsignedInt() {
        return data.readUnsignedInt();
    }

    @Override
    public long readUnsignedIntLE() {
        return data.readUnsignedIntLE();
    }

    @Override
    public long readLong() {
        return data.readLong();
    }

    @Override
    public long readLongLE() {
        return data.readLongLE();
    }

    @Override
    public char readChar() {
        return data.readChar();
    }

    @Override
    public float readFloat() {
        return data.readFloat();
    }

    @Override
    public double readDouble() {
        return data.readDouble();
    }

    @Override
    public ByteBuf readBytes(int i) {
        return data.readBytes(i);
    }

    @Override
    public ByteBuf readSlice(int i) {
        return data.readSlice(i);
    }

    @Override
    public ByteBuf readRetainedSlice(int i) {
        return data.readRetainedSlice(i);
    }

    @Override
    public ByteBuf readBytes(ByteBuf byteBuf) {
        return data.readBytes(byteBuf);
    }

    @Override
    public ByteBuf readBytes(ByteBuf byteBuf, int i) {
        return data.readBytes(byteBuf, i);
    }

    @Override
    public ByteBuf readBytes(ByteBuf byteBuf, int i, int i1) {
        return data.readBytes(byteBuf, i, i1);
    }

    @Override
    public ByteBuf readBytes(byte[] bytes) {
        return data.readBytes(bytes);
    }

    @Override
    public ByteBuf readBytes(byte[] bytes, int i, int i1) {
        return data.readBytes(bytes, i, i1);
    }

    @Override
    public ByteBuf readBytes(ByteBuffer byteBuffer) {
        return data.readBytes(byteBuffer);
    }

    @Override
    public ByteBuf readBytes(OutputStream outputStream, int i) throws IOException {
        return data.readBytes(outputStream, i);
    }

    @Override
    public int readBytes(GatheringByteChannel gatheringByteChannel, int i) throws IOException {
        return data.readBytes(gatheringByteChannel, i);
    }

    @Override
    public CharSequence readCharSequence(int i, Charset charset) {
        return data.readCharSequence(i, charset);
    }

    @Override
    public int readBytes(FileChannel fileChannel, long l, int i) throws IOException {
        return data.readBytes(fileChannel, l, i);
    }

    @Override
    public ByteBuf skipBytes(int i) {
        return data.skipBytes(i);
    }

    @Override
    public ByteBuf writeBoolean(boolean b) {
        return data.writeBoolean(b);
    }

    @Override
    public ByteBuf writeByte(int i) {
        return data.writeByte(i);
    }

    @Override
    public ByteBuf writeShort(int i) {
        return data.writeShort(i);
    }

    @Override
    public ByteBuf writeShortLE(int i) {
        return data.writeShortLE(i);
    }

    @Override
    public ByteBuf writeMedium(int i) {
        return data.writeMedium(i);
    }

    @Override
    public ByteBuf writeMediumLE(int i) {
        return data.writeMediumLE(i);
    }

    @Override
    public ByteBuf writeInt(int i) {
        return data.writeInt(i);
    }

    @Override
    public ByteBuf writeIntLE(int i) {
        return data.writeIntLE(i);
    }

    @Override
    public ByteBuf writeLong(long l) {
        return data.writeLong(l);
    }

    @Override
    public ByteBuf writeLongLE(long l) {
        return data.writeLongLE(l);
    }

    @Override
    public ByteBuf writeChar(int i) {
        return data.writeChar(i);
    }

    @Override
    public ByteBuf writeFloat(float v) {
        return data.writeFloat(v);
    }

    @Override
    public ByteBuf writeDouble(double v) {
        return data.writeDouble(v);
    }

    @Override
    public ByteBuf writeBytes(ByteBuf byteBuf) {
        return data.writeBytes(byteBuf);
    }

    @Override
    public ByteBuf writeBytes(ByteBuf byteBuf, int i) {
        return data.writeBytes(byteBuf, i);
    }

    @Override
    public ByteBuf writeBytes(ByteBuf byteBuf, int i, int i1) {
        return data.writeBytes(byteBuf, i, i1);
    }

    @Override
    public ByteBuf writeBytes(byte[] bytes) {
        return data.writeBytes(bytes);
    }

    @Override
    public ByteBuf writeBytes(byte[] bytes, int i, int i1) {
        return data.writeBytes(bytes, i, i1);
    }

    @Override
    public ByteBuf writeBytes(ByteBuffer byteBuffer) {
        return data.writeBytes(byteBuffer);
    }

    @Override
    public int writeBytes(InputStream inputStream, int i) throws IOException {
        return data.writeBytes(inputStream, i);
    }

    @Override
    public int writeBytes(ScatteringByteChannel scatteringByteChannel, int i) throws IOException {
        return data.writeBytes(scatteringByteChannel, i);
    }

    @Override
    public int writeBytes(FileChannel fileChannel, long l, int i) throws IOException {
        return data.writeBytes(fileChannel, l, i);
    }

    @Override
    public ByteBuf writeZero(int i) {
        return data.writeZero(i);
    }

    @Override
    public int writeCharSequence(CharSequence charSequence, Charset charset) {
        return data.writeCharSequence(charSequence, charset);
    }

    @Override
    public int indexOf(int i, int i1, byte b) {
        return data.indexOf(i, i1, b);
    }

    @Override
    public int bytesBefore(byte b) {
        return data.bytesBefore(b);
    }

    @Override
    public int bytesBefore(int i, byte b) {
        return data.bytesBefore(i, b);
    }

    @Override
    public int bytesBefore(int i, int i1, byte b) {
        return data.bytesBefore(i, i1, b);
    }

    @Override
    public int forEachByte(ByteProcessor byteProcessor) {
        return data.forEachByte(byteProcessor);
    }

    @Override
    public int forEachByte(int i, int i1, ByteProcessor byteProcessor) {
        return data.forEachByte(i, i1, byteProcessor);
    }

    @Override
    public int forEachByteDesc(ByteProcessor byteProcessor) {
        return data.forEachByteDesc(byteProcessor);
    }

    @Override
    public int forEachByteDesc(int i, int i1, ByteProcessor byteProcessor) {
        return data.forEachByteDesc(i, i1, byteProcessor);
    }

    @Override
    public ByteBuf copy() {
        return data.copy();
    }

    @Override
    public ByteBuf copy(int i, int i1) {
        return data.copy(i, i1);
    }

    @Override
    public ByteBuf slice() {
        return data.slice();
    }

    @Override
    public ByteBuf retainedSlice() {
        return data.retainedSlice();
    }

    @Override
    public ByteBuf slice(int i, int i1) {
        return data.slice(i, i1);
    }

    @Override
    public ByteBuf retainedSlice(int i, int i1) {
        return data.retainedSlice(i, i1);
    }

    @Override
    public ByteBuf duplicate() {
        return data.duplicate();
    }

    @Override
    public ByteBuf retainedDuplicate() {
        return data.retainedDuplicate();
    }

    @Override
    public int nioBufferCount() {
        return data.nioBufferCount();
    }

    @Override
    public ByteBuffer nioBuffer() {
        return data.nioBuffer();
    }

    @Override
    public ByteBuffer nioBuffer(int i, int i1) {
        return data.nioBuffer(i, i1);
    }

    @Override
    public ByteBuffer internalNioBuffer(int i, int i1) {
        return data.internalNioBuffer(i, i1);
    }

    @Override
    public ByteBuffer[] nioBuffers() {
        return data.nioBuffers();
    }

    @Override
    public ByteBuffer[] nioBuffers(int i, int i1) {
        return data.nioBuffers(i, i1);
    }

    @Override
    public boolean hasArray() {
        return data.hasArray();
    }

    @Override
    public byte[] array() {
        return data.array();
    }

    @Override
    public int arrayOffset() {
        return data.arrayOffset();
    }

    @Override
    public boolean hasMemoryAddress() {
        return data.hasMemoryAddress();
    }

    @Override
    public long memoryAddress() {
        return data.memoryAddress();
    }

    @Override
    public String toString(Charset charset) {
        return data.toString();
    }

    @Override
    public String toString(int i, int i1, Charset charset) {
        return data.toString(i, i1, charset);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return data.equals(o);
    }

    @Override
    public int compareTo(ByteBuf byteBuf) {
        return data.compareTo(byteBuf);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public ByteBuf retain(int i) {
        return data.retain(i);
    }

    @Override
    public int refCnt() {
        return data.refCnt();
    }

    @Override
    public ByteBuf retain() {
        return data.retain();
    }

    @Override
    public ByteBuf touch() {
        return data.touch();
    }

    @Override
    public ByteBuf touch(Object o) {
        return data.touch(o);
    }

    @Override
    public boolean release() {
        return data.release();
    }

    @Override
    public boolean release(int i) {
        return data.release(i);
    }

    public int readVarInt() {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = this.readByte();
            int value = read & 0b01111111;
            result |= value << 7 * numRead;
            numRead++;
            if (numRead > 5) { throw new RuntimeException("VarInt is too big"); }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    public PacketBuffer writeVarInt(int value) {
        do {
            byte temp = (byte) (value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= (byte) 0b10000000;
            }
            this.writeByte(temp);
        } while (value != 0);
        return this;
    }

    public String readUtfString() {
        return this.readUtfString(32767);
    }

    public PacketBuffer writeUtfString(String value) {
        return this.writeUtfString(value, 32767);
    }

    public String readUtfString(int length) {
        int len = this.readVarInt();

        if (len > length) {
            throw new RuntimeException("Exceeding Expected Values: " + length);
        } else {
            byte[] data = new byte[len];
            this.readBytes(data);
            return new String(data, StandardCharsets.UTF_8);
        }
    }

    public PacketBuffer writeUtfString(String value, int length) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > length) {
            throw new RuntimeException("Exceeding Expected Values: " + length);
        } else {
            this.writeVarInt(bytes.length);
            this.writeBytes(bytes);
        }

        return this;
    }

    public PacketBuffer writePosition(Position position) {
        this.writeLong(position.toLong());
        return this;
    }

    public Position readPosition() {
        return Position.toPosition(this.readLong());
    }

    public <C> PacketBuffer writeCollection(Collection<C> collection, BiConsumer<PacketBuffer, C> runnable) {
        this.writeVarInt(collection.size());

        for (C c : collection) {
            runnable.accept(this, c);
        }

        return this;
    }

    public <C> Collection<C> readCollection(java.util.function.Function<PacketBuffer, C> runnable) {
        Collection<C> collection = new ArrayList<>(Collections.emptySet());
        int length = this.readVarInt();

        for (int i = 0; i < length; i++) {
            collection.add(runnable.apply(this));
        }

        return collection;
    }
}