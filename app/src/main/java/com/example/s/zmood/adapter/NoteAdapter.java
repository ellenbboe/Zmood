package com.example.s.zmood.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.s.zmood.NoteActivity;
import com.example.s.zmood.R;
import com.example.s.zmood.entity.NoteEntity;

import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.example.s.zmood.NoteActivity.NOTEDATE;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Context context;
    private List<NoteEntity> notes;

    public NoteAdapter(List<NoteEntity> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null)
        {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.simplenote,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if(position<0){
                    Log.d(TAG, "onClick:"+position);
                    return;
                }
                NoteEntity note = notes.get(position);
                Intent intent = new Intent(context,NoteActivity.class);
                intent.putExtra(NoteActivity.IMAGEID,note.getImageResourceId());
                intent.putExtra(NoteActivity.NOTETITLE,note.getTitle());
                intent.putExtra(NoteActivity.NOTEDATE,note.getDate());
                intent.putExtra(NoteActivity.NOTECONTEXT,note.getDescription());
                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteEntity note = notes.get(position);
        holder.notedate.setText(note.getDate());
        holder.notetitle.setText(note.getTitle());
        Glide.with(context).load(note.getImageResourceId()).into(holder.noteimage);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView noteimage;
        TextView notetitle;
        TextView notedate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView =(CardView) itemView;
            noteimage = itemView.findViewById(R.id.noteimage);
            notetitle = itemView.findViewById(R.id.notetitle);
            notedate = itemView.findViewById(R.id.notedate);

        }
    }
}
