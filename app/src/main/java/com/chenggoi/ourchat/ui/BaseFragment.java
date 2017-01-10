package com.chenggoi.ourchat.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by chenggoi on 16-12-6.
 */

public class BaseFragment extends Fragment {
    public void startActivity(Class<? extends Activity> c, Bundle bundle) {
        Intent intent = new Intent(getActivity(), c);
        if (bundle != null) {
            intent.putExtra(getActivity().getPackageName(), bundle);
        }
        startActivity(intent);
    }
}
