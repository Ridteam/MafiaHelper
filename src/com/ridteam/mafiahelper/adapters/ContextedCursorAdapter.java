package com.ridteam.mafiahelper.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;

public abstract class ContextedCursorAdapter<Holder> extends CursorAdapter implements IContextedAdapter {
	private LayoutInflater mInflater;
	private int mLayoutId;
	private OnContextButtonClickListener mOnContextButtonClickListener;

	public ContextedCursorAdapter(Context context, Cursor c, int layout, int flags) {
		super(context, c, flags);
		mInflater = LayoutInflater.from(context);
		mLayoutId = layout;
		findColumns();
	}

	abstract void findColumns();
	abstract Holder newHolder(View view);
	abstract void bindHolder(Holder holder, Context context, Cursor cursor);
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		@SuppressWarnings("unchecked")
		Holder holder = (Holder) view.getTag();
		if(holder == null) {
			holder = newHolder(view);
			view.setTag(holder);
		}
		
		bindHolder(holder, context, cursor);
	}
	
    @Override
    public Cursor swapCursor(Cursor cursor) {
        Cursor res = super.swapCursor(cursor);
        findColumns();
        return res;
    }

	@Override
	public void setOnContextButtonClickListener(
			OnContextButtonClickListener onContextMenuButtonClickListener) {
		mOnContextButtonClickListener = onContextMenuButtonClickListener;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return inflate(mLayoutId, null);
	}
	
	View inflate(int resource, ViewGroup root) {
		return mInflater.inflate(resource, root);
	}
	
	void handleContextButtonClick(View targetView, ContextHolder contextHolder){
		if(mOnContextButtonClickListener != null) {
			AdapterContextMenuInfo menuInfo = new AdapterContextMenuInfo(targetView, contextHolder.position, contextHolder.id);
			mOnContextButtonClickListener.onContextButtonClick(menuInfo);
		}
	}
	
	class ContextHolder {
		public int position;
		public long id;
	}
}
