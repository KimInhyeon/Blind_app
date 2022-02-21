package com.ksinfo.tomodomo.module;

import android.app.Application;
import android.os.StrictMode;
import android.widget.ArrayAdapter;

import com.ksinfo.tomodomo.model.itf.BoardInterface;
import com.ksinfo.tomodomo.model.vo.board.BoardVO;

import java.io.IOException;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BoardModule {
    private final Application application;

    public BoardModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @Named("boardSliderList")
    public List<BoardVO> provideBoardSliderList(BoardInterface boardInterface) {
        StrictMode.ThreadPolicy policy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        List<BoardVO> boardSliderList = null;
        try {
            boardSliderList = boardInterface.getBoardSliderList().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            StrictMode.setThreadPolicy(policy);
        }

        return boardSliderList;
    }

    @Provides
    @Singleton
    @Named("boardList")
    public List<BoardVO> provideBoardList(BoardInterface boardInterface) {
        StrictMode.ThreadPolicy policy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        List<BoardVO> boardList = null;
        try {
            boardList = boardInterface.getBoardList().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            StrictMode.setThreadPolicy(policy);
        }

        return boardList;
    }

    @Provides
    @Singleton
    public ArrayAdapter<BoardVO> provideArrayAdapter(@Named("boardList") List<BoardVO> boardList) {
        return new ArrayAdapter<BoardVO>(
            application.getApplicationContext()
          , android.R.layout.simple_spinner_dropdown_item
          , boardList
        ) {
            @Override
            public long getItemId(int position) {
                return super.getItem(position).getBoardId();
            }
        };
    }
}