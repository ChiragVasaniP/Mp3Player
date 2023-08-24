package com.sdplayer.tollsplayer.presentation.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdplayer.tollsplayer.databinding.CardViewBinding;
import com.sdplayer.tollsplayer.domain.models.AudioModel;

public class ListHolder extends RecyclerView.ViewHolder {

    CardViewBinding binding;

    public ListHolder(@NonNull View itemView) {
        super(itemView);
        binding = CardViewBinding.bind(itemView);
    }

    public void bind(AudioModel musicData){
        binding.tvName.setText(musicData.name);
        binding.tvData.setText(musicData.data);
    }

    public CardViewBinding getCardView() {
        return binding;
    }
}