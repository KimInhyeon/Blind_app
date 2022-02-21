package com.ksinfo.tomodomo.model.vo.board;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(name = "paragraph", value = ParagraphBlock.class),
    @JsonSubTypes.Type(name = "image", value = ImageBlock.class)
})
public abstract class PostBlock implements Parcelable {
    private final String type;
    protected final Data data;

    public static final Creator<PostBlock> CREATOR = new Creator<PostBlock>() {
        @Override
        public PostBlock createFromParcel(Parcel in) {
            String type = in.readString();
            switch (type) {
                case "paragraph":
                    return new ParagraphBlock(type, in);
                case "image":
                    return new ImageBlock(type, in);
                default:
                    return null;
            }
        }

        @Override
        public PostBlock[] newArray(int size) {
            return new PostBlock[size];
        }
    };

    public static abstract class Data implements Parcelable {
        @Override
        public final int describeContents() {
            return 0;
        }

        public abstract String toString();
    }

    protected PostBlock(String type, Data data) {
        this.type = type;
        this.data = data;
    }

    public final String getType() {
        return type;
    }

    public final Data getData() {
        return data;
    }

    @Override
    public final String toString() {
        return "{type:\"" + type + "\",data:" + data + '}';
    }

    @Override
    public final int describeContents() {
        return 0;
    }

    @Override
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            parcel.writeValue(data);
        } else {
            parcel.writeTypedObject(data, i);
        }
    }
}