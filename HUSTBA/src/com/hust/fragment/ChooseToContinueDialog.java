package com.hust.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.hust.hustbaer.WritingNewFile;

public class ChooseToContinueDialog extends DialogFragment {
	 @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage("提交成功，是否继续提交？")
	               .setPositiveButton("退出", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       getActivity().finish();
	                   }
	               })
	               .setNegativeButton("确定", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	   Intent intent = new Intent();
	                	   intent.setClass(getActivity(), WritingNewFile.class);
	                	   getActivity().startActivity(intent);
	                	   getActivity().finish();
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }

}
