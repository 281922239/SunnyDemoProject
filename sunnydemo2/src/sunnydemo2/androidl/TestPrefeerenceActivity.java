package sunnydemo2.androidl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2016/1/5.
 * 首选项设置，
 */
public class TestPrefeerenceActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener{

    public static final String TAG = TestPrefeerenceActivity.class.getSimpleName();

    private SharedPreferences mSharePreference;

    private ListPreference mListPreference;
    private CheckBoxPreference mCBPreferenceMan;
    private CheckBoxPreference mCBPreferenceWoman;
    private EditTextPreference mEditPreference;


    public static void startTestPreferenceActivity(Context context){
        Intent targetIntent = new Intent(context,TestPrefeerenceActivity.class);
        context.startActivity(targetIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_test_preference);
        addPreferencesFromResource(R.xml.test_preference);

        mSharePreference = PreferenceManager.getDefaultSharedPreferences(this);

        mListPreference = (ListPreference) findPreference("city_choose");
        mCBPreferenceMan = (CheckBoxPreference) findPreference("preference_sex_man");
        mCBPreferenceWoman = (CheckBoxPreference) findPreference("preference_sex_woman");
        mEditPreference = (EditTextPreference) findPreference("nickName_edit");

        mEditPreference.setOnPreferenceChangeListener(this);
        mListPreference.setOnPreferenceChangeListener(this);
        mCBPreferenceWoman.setOnPreferenceChangeListener(this);
        mCBPreferenceMan.setOnPreferenceChangeListener(this);
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if(preference instanceof EditTextPreference){
            mEditPreference.setTitle(newValue.toString());
            mEditPreference.setText(newValue.toString());
            mEditPreference.setDefaultValue(newValue);
        }else if(preference instanceof ListPreference){

            mListPreference.setValue(newValue.toString());
            mListPreference.setTitle(newValue.toString());
        }else if(preference instanceof CheckBoxPreference){
          changeCBState(preference);
        }
        return false;
    }

    private void changeCBState(Preference preference) {
        if(preference.getKey().equals("preference_sex_man")){
            checkCheckBoxPreference(preference,mCBPreferenceWoman);
        }else{
            checkCheckBoxPreference(preference,mCBPreferenceMan);
        }
    }

    /**
     * 选中相反的CheckBoxPreference;
     * @param preference
     * @param mCBPreferenceWoman
     */
    private void checkCheckBoxPreference(Preference preference, CheckBoxPreference mCBPreferenceWoman) {
        if(((CheckBoxPreference)preference).isChecked()){
            ((CheckBoxPreference) preference).setChecked(false);
            mCBPreferenceWoman.setChecked(true);
        }else{
            ((CheckBoxPreference) preference).setChecked(true);
            mCBPreferenceWoman.setChecked(false);
        }
    }
}
