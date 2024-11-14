package ru.volga.online.gui.dialogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.volga.online.databinding.DialogItemOldBinding;
import ru.volga.online.gui.util.Utils;
public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.ViewHolder> {
    private int currentSelectedPosition = 0;
    private View currentSelectedView;
    private final ArrayList<TextView> fieldHeaders;
    private final ArrayList<String> fieldTexts;
    private final ArrayList<ArrayList<TextView>> fields;
    private OnClickListener onClickListener;
    private OnDoubleClickListener onDoubleClickListener;

    public interface OnClickListener {
        void onClick(int position, String text);
    }

    public interface OnDoubleClickListener {
        void onDoubleClick();
    }

    public DialogAdapter(ArrayList<String> fields, ArrayList<TextView> fieldHeaders) {
        this.fieldTexts = fields;
        this.fieldHeaders = fieldHeaders;
        this.fields = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DialogItemOldBinding binding = DialogItemOldBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] headers = this.fieldTexts.get(position).split("\t");
        ArrayList<TextView> fieldList = new ArrayList<>();
        for (int i = 0; i < headers.length; i++) {
            TextView field = (TextView) holder.binding.dialogItemMain.getChildAt(i);
            field.setText(Utils.transfromColors(headers[i].replace("\\t", "")));
            field.setVisibility(View.VISIBLE);
            fieldList.add(field);
        }
        this.fields.add(fieldList);

        if (this.currentSelectedPosition == position) {
            currentSelectedView = holder.binding.itemBg;
            currentSelectedView.setVisibility(View.VISIBLE);
            this.onClickListener.onClick(position, ((TextView)holder.binding.dialogItemMain.getChildAt(0)).getText().toString());
        } else {
            holder.binding.itemBg.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(view -> {
            if (this.currentSelectedPosition != holder.getAdapterPosition()) {
                if (currentSelectedView != null) {
                    currentSelectedView.setVisibility(View.GONE);
                }
                this.currentSelectedPosition = holder.getAdapterPosition();
                this.currentSelectedView = holder.binding.itemBg;
                holder.binding.itemBg.setVisibility(View.VISIBLE);
                this.onClickListener.onClick(holder.getAdapterPosition(), ((TextView)holder.binding.dialogItemMain.getChildAt(0)).getText().toString());
            } else if (onDoubleClickListener != null) {
                onDoubleClickListener.onDoubleClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.fieldTexts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final DialogItemOldBinding binding;

        public ViewHolder(DialogItemOldBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void updateSizes() {
        int[] max = new int[4];
        for (ArrayList<TextView> row : this.fields) {
            for (int j = 0; j < row.size(); j++) {
                int width = row.get(j).getWidth();
                if (max[j] < width) {
                    max[j] = width;
                }
            }
        }
        for (int i = 0; i < max.length; i++) {
            int headerWidth = this.fieldHeaders.get(i).getWidth();
            if (max[i] < headerWidth) {
                max[i] = headerWidth;
            }
        }
        for (ArrayList<TextView> row : this.fields) {
            for (int j = 0; j < row.size(); j++) {
                row.get(j).setWidth(max[j]);
            }
        }
        for (int i = 0; i < this.fieldHeaders.size(); i++) {
            this.fieldHeaders.get(i).setWidth(max[i]);
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    public void setOnDoubleClickListener(OnDoubleClickListener listener) {
        this.onDoubleClickListener = listener;
    }
}
