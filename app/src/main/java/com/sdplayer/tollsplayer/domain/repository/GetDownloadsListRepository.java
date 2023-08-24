package com.sdplayer.tollsplayer.domain.repository;


import com.sdplayer.tollsplayer.domain.models.AudioModel;

import java.util.ArrayList;

public interface GetDownloadsListRepository {
    ArrayList<AudioModel> getData();
}
