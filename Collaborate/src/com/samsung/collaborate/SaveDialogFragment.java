package com.samsung.collaborate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

public class SaveDialogFragment extends DialogFragment {

	 @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		builder .setTitle("Save File")
				.setView(inflater.inflate(R.layout.dialog_save, null))
				.setPositiveButton(R.string.textsave, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				})
				.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				})
				.setNeutralButton(R.string.browse, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
		return builder.create();
	}
}
