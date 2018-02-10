package com.twenty.four.crafts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
public class FoldingCellListAdapter extends ArrayAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;


    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get item for selected view
        Item item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder
            viewHolder.locationAudition = (TextView) cell.findViewById(R.id.locationAudition);
            viewHolder.dateAudition = (TextView) cell.findViewById(R.id.dateAudition);
            viewHolder.timeAudition = (TextView) cell.findViewById(R.id.timeAudition);
            viewHolder.projectName = (TextView) cell.findViewById(R.id.ProjectName);
            viewHolder.projectType = (TextView) cell.findViewById(R.id.ProjectType);
            viewHolder.projectDescription = (TextView) cell.findViewById(R.id.ProjectDescription);
            viewHolder.contentRequestBtn = (TextView) cell.findViewById(R.id.content_request_btn);
            viewHolder.date = (TextView) cell.findViewById(R.id.publishDate);
            viewHolder.innerProjectName = cell.findViewById(R.id.innerProjectname);
            viewHolder.innerImageURL = cell.findViewById(R.id.head_image);
            viewHolder.innerName = cell.findViewById(R.id.content_name_view);
            viewHolder.innerPhoneNumber = cell.findViewById(R.id.content_phoneno_view);
            viewHolder.innerApplnFrom = cell.findViewById(R.id.content_from_date_1);
            viewHolder.innerApplnTo = cell.findViewById(R.id.content_to_date_1);
            viewHolder.innerAuditionDate = cell.findViewById(R.id.content_audition_date);
            viewHolder.innerAuditionTime = cell.findViewById(R.id.content_audition_time);
            viewHolder.innerAuditionLocation = cell.findViewById(R.id.content_audition_location);
            viewHolder.innerProjectDescription = cell.findViewById(R.id.content_project_desc);

            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        // bind data from selected element to view through view holder
        viewHolder.locationAudition.setText(item.getLocation());
        viewHolder.dateAudition.setText(item.getAuditionDate());
        viewHolder.timeAudition.setText(item.getAuditionTime());
        viewHolder.projectName.setText(item.getProjectName());
        viewHolder.projectType.setText(item.getProjectType());
        viewHolder.projectDescription.setText(item.getProjectDescription());
        viewHolder.innerProjectName.setText(item.getProjectName());
        //include image
        viewHolder.innerName.setText(item.getInnerName());
        viewHolder.innerPhoneNumber.setText(item.getInnerPhoneNumber());
        viewHolder.innerApplnFrom.setText(item.getInnerApplnFrom());
        viewHolder.innerApplnTo.setText(item.getInnerApplnTo());
        viewHolder.innerAuditionDate.setText(item.getAuditionDate());
        viewHolder.innerAuditionTime.setText(item.getAuditionTime());
        viewHolder.innerAuditionLocation.setText(item.getInnerAuditionLocation());
        viewHolder.innerProjectDescription.setText(item.getInnerProjectDescription());



        // set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }


        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }



    // View lookup cache
    private static class ViewHolder {

        ImageView innerImageURL;
        TextView innerProjectName;
        TextView innerPhoneNumber;
        TextView innerName;
        TextView innerApplnFrom;
        TextView innerApplnTo;
        TextView innerAuditionDate;
        TextView innerAuditionTime;
        TextView innerAuditionLocation;
        TextView innerProjectDescription;


        TextView locationAudition;
        TextView contentRequestBtn;
        TextView dateAudition;
        TextView timeAudition;
        TextView projectName;
        TextView projectType;
        TextView date;
        TextView projectDescription;
    }
}
