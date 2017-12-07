/*
 *  COPYRIGHT NOTICE  
 *  Copyright (C) 2016, Jhuster <lujun.hust@gmail.com>
 *  https://github.com/Jhuster/AudioDemo
 *   
 *  @license under the Apache License, Version 2.0 
 *
 *  @file    WavFileHeader.java
 *  
 *  @version 1.0     
 *  @author  Jhuster
 *  @date    2016/03/19
 */
package com.example.daixiansen.audiostudydemo.api.wav;

public class WavFileHeader {

    public static final int WAV_FILE_HEADER_SIZE = 44;
    public static final int WAV_CHUNKSIZE_EXCLUDE_DATA = 36;

    public static final int WAV_CHUNKSIZE_OFFSET = 4;
    public static final int WAV_SUB_CHUNKSIZE1_OFFSET = 16;
    public static final int WAV_SUB_CHUNKSIZE2_OFFSET = 40;

    public String mChunkID = "RIFF";        // "RIFF" 标志, 大写
    public int mChunkSize = 0;              // 文件长度。这个长度不包括"RIFF"标志 和文件长度 本身所占字节, 下面的子块大小也是这样。
    public String mFormat = "WAVE";         // "WAVE" 类型块标识, 大写

    public String mSubChunk1ID = "fmt ";    //表示"fmt " chunk的开始。此块中包括文件内部格式信息。小写, 最后一个字符是空格。
    public int mSubChunk1Size = 16;         // 文件内部格式信息数据的大小
    public short mAudioFormat = 1;          // 音频数据的编码方式。1 表示是 PCM 编码    1以外的值表示一些压缩形式
    public short mNumChannel = 1;           // 声道数  Mono = 1，Stereo = 2
    public int mSampleRate = 8000;          // 采样率(每秒样本数), 比如 44100 等
    public int mByteRate = 0;               // 音频数据传送速率, 单位是字节   SampleRate * NumChannels(声道) * BitsPerSample(采样精度) / 8
    public short mBlockAlign = 0;           // 每次采样的大小 = 采样精度 * 声道数/8(单位是字节); 这也是字节对齐的最小单位, 譬如 16bit 立体声在这里的值是 4 字节。播放软件需要 一次处理多个该值大小的字节数据，以便将其值用于缓冲区的调整。
    public short mBitsPerSample = 8;        // 每个声道的采样精度; 譬如 16bit 在这里的值就是16。如果有多个声道，则每个声道的采样精度大小都一样的

    public String mSubChunk2ID = "data";    //  表示 "data" chunk的开始。此块中包含音频数据。小写
    public int mSubChunk2Size = 0;          //  音频数据的长度

    public WavFileHeader() {

    }

    public WavFileHeader(int sampleRateInHz, int channels, int bitsPerSample) {
        mSampleRate = sampleRateInHz;
        mBitsPerSample = (short) bitsPerSample;
        mNumChannel = (short) channels;
        mByteRate = mSampleRate * mNumChannel * mBitsPerSample / 8;
        mBlockAlign = (short) (mNumChannel * mBitsPerSample / 8);
    }
}

