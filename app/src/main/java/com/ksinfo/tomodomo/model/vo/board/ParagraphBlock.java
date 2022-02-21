package com.ksinfo.tomodomo.model.vo.board;

import android.os.Build;
import android.os.Parcel;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ParagraphBlock extends PostBlock {

    public static final class ParagraphData extends Data {
        private final String text;

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new ParagraphData(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new ParagraphData[size];
            }
        };

        public ParagraphData(@JsonProperty("text") String text) {
            this.text = text;
        }

        private ParagraphData(Parcel in) {
            text = in.readString();
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "{text:\"" + text.replaceAll("\\\"", "\\\\\"") + "\"}";
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(text);
        }
    }

    public ParagraphBlock(
        @JsonProperty("type") String type, @JsonProperty("data") ParagraphData data
    ) {
        super(type, data);
    }

    protected ParagraphBlock(String type, Parcel in) {
        super(type, Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                    ? (ParagraphData) in.readValue(ParagraphData.class.getClassLoader())
                    : in.readTypedObject(ParagraphData.CREATOR)
        );
    }
}