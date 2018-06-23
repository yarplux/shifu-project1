/*
 * Copyright 2016 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shifu.user.project1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class CustomAdapter extends RealmRecyclerViewAdapter<RealmModel, CustomAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView textView;
        private Long id;

        public RelativeLayout viewBackground, viewForeground;

        public ViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.content);
            viewForeground = v.findViewById(R.id.view_foreground);

            viewForeground.setClickable(true);
            viewForeground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        final View v = view;
                        FragmentActivity activity = (FragmentActivity) v.getContext();
                        Fragment frag2 = new NewFragment();
                        FragmentTransaction fTrans = activity.getSupportFragmentManager().beginTransaction();
                        fTrans.replace(R.id.container, frag2);
                        fTrans.addToBackStack(null);
                        fTrans.commit();
                    }

            });

            viewBackground = v.findViewById(R.id.view_background);

        }

        public TextView getTextView() {
            return textView;
        }

        public void setId (Long id) {
            this.id = id;
        }
        public Long getId () {
            return this.id;
        }
    }

    public CustomAdapter(OrderedRealmCollection<RealmModel> data) {
        super(data, true);
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_item, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        //viewHolder.getTextView().setText(tmap.get(Long.valueOf(position)));
        final RealmModel obj = getItem(position);
        viewHolder.getTextView().setText(obj.getName());
        viewHolder.setId(obj.getID());
        Log.d("ID placed:", Long.toString(viewHolder.getId()));
    }

    public Long ItemID(int position) {
        return getItem(position).getID();
    }

}
