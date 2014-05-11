package com.icannhas.readysaster;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SwiperTabActivity extends BaseActivity implements ActionBar.TabListener {

	private Button mSaveButton;
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;

	private PersonalDetailsTabFragment mPersonalDetailsTabFragment;
	private LocationDetailsFragment mLocationDetailsFragment;
	private StructureDetailsTabFragment mStructureDetailsTabFragment;
	private DataListFragment mDataListFragment;

	private List<BasicTabFragment> mTabsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swiper_tab);

		mTabsList = new ArrayList<BasicTabFragment>();

		setupViews();
		setupListeners();
	}

	public void setupViews() {

		mSaveButton = (Button) findViewById(R.id.save_button);
		setupPager();
	}

	public void setupListeners() {
		mSaveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				printDetails();

			}
		});
	}

	public void setupPager() {

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}

	public void printDetails() {
		StringBuilder details = new StringBuilder();
		for (int i = 0; i < mTabsList.size(); i++) {
			BasicTabFragment fragment = mTabsList.get(i);
			details.append(fragment.toJsonString());
			details.append("\n");
		}
		Log.e("details", details.toString());
		try {
			DaoSession daoSession = ReadysasterApplication.getInstance().getDaoSession();
			DataDao dataDao = daoSession.getDataDao();
			PersonalDetails personal = mPersonalDetailsTabFragment.getPersonalDetails();
			LocationDetails location = mLocationDetailsFragment.getLocationDetails();
			StructureDetails structure = mStructureDetailsTabFragment.getStructureDetails();
			PersonalDetailsDao personalDao = daoSession.getPersonalDetailsDao();
			StructureDetailsDao structureDao = daoSession.getStructureDetailsDao();
			LocationDetailsDao locationDao = daoSession.getLocationDetailsDao();
			Data data = new Data();
			personalDao.insert(personal);
			structureDao.insert(structure);
			locationDao.insert(location);
			dataDao.insert(data);

			data.setPersonalDetails(personal);
			data.setLocationDetails(location);
			data.setStructureDetails(structure);

			dataDao.update(data);
			personalDao.update(personal);
			structureDao.update(structure);
			locationDao.update(location);

			Log.e("name", personal.getName());
			Log.e("name", data.getPersonalDetails().getName());

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "Some details are incorrect", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.swiper_tab, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			if (position == 0) {
				mPersonalDetailsTabFragment = new PersonalDetailsTabFragment();
				mTabsList.add(mPersonalDetailsTabFragment);
				return mPersonalDetailsTabFragment;
			} else if (position == 1) {
				mLocationDetailsFragment = new LocationDetailsFragment();
				mTabsList.add(mLocationDetailsFragment);
				return mLocationDetailsFragment;
			} else if (position == 2) {
				mStructureDetailsTabFragment = new StructureDetailsTabFragment();
				mTabsList.add(mStructureDetailsTabFragment);
				return mStructureDetailsTabFragment;
			} else {
				mDataListFragment = new DataListFragment();
				return mDataListFragment;
			}

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {

			case 0:
				return getString(R.string.title_section1);
			case 1:
				return getString(R.string.title_section2);
			case 2:
				return getString(R.string.title_section3);
			case 3:
				return getString(R.string.title_section4);
			}
			return null;
		}
	}

}
