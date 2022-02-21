package com.ksinfo.tomodomo.model.vo.board;

import android.os.Build;
import android.os.Parcel;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ImageBlock extends PostBlock {

    public static final class ImageData extends Data {
        private final String url;
        private final String caption;
        private final boolean withBorder;
        private final boolean withBackground;
        private final boolean stretched;

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new ImageData(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new ImageData[size];
            }
        };

        public ImageData(
            @JsonProperty("url") String url,
            @JsonProperty("caption") String caption,
            @JsonProperty("withBorder") boolean withBorder,
            @JsonProperty("withBackground") boolean withBackground,
            @JsonProperty("stretched") boolean stretched
        ) {
            this.url = url;
            this.caption = caption;
            this.withBorder = withBorder;
            this.withBackground = withBackground;
            this.stretched = stretched;
        }

        private ImageData(Parcel in) {
            url = in.readString();
            caption = in.readString();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                withBorder = in.readByte() != 0;
                withBackground = in.readByte() != 0;
                stretched = in.readByte() != 0;
            } else {
                withBorder = in.readBoolean();
                withBackground = in.readBoolean();
                stretched = in.readBoolean();
            }
        }

        public String getUrl() {
            return url;
        }

        public String getCaption() {
            return caption;
        }

        public boolean isWithBorder() {
            return withBorder;
        }

        public boolean isWithBackground() {
            return withBackground;
        }

        public boolean isStretched() {
            return stretched;
        }

        @Override
        public String toString() {
            return "{url:\"" + url + "\",caption:\"" + caption + "\",withBorder:" + withBorder
                 + ",withBackground:" + withBackground + ",stretched:" + stretched + '}';
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(url);
            parcel.writeString(caption);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                parcel.writeByte((byte) (withBorder ? 1 : 0));
                parcel.writeByte((byte) (withBackground ? 1 : 0));
                parcel.writeByte((byte) (stretched ? 1 : 0));
            } else {
                parcel.writeBoolean(withBorder);
                parcel.writeBoolean(withBackground);
                parcel.writeBoolean(stretched);
            }
        }
    }

    public ImageBlock(@JsonProperty("type") String type, @JsonProperty("data") ImageData data) {
        super(type, data);
    }

    protected ImageBlock(String type, Parcel in) {
        super(type, Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                    ? (ImageData) in.readValue(ImageData.class.getClassLoader())
                    : in.readTypedObject(ImageData.CREATOR)
        );
    }
}