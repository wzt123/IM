/*******************************************************************************
 * Copyright (c) 1999, 2014 IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution. 
 *
 * The Eclipse Public License is available at 
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 *   http://www.eclipse.org/org/documents/edl-v10.php.
 */
package com.example.lsy.myapp.mqtt;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * The connection details activity operates the fragments that make up the
 * connection details screen.
 * <p>
 * The fragments which this FragmentActivity uses are
 * <ul>
 * <li>{@link /*HistoryFragment}
 * <li>{@link /*PublishFragment}
 * <li>{@link /*SubscribeFragment}
 * </ul>
 * 
 */
public class ConnectionDetails extends FragmentActivity implements
    ActionBar.TabListener {

  /**
   * {@link SectionsPagerAdapter} that is used to get pages to display
   */
  SectionsPagerAdapter sectionsPagerAdapter;
  /**
   * {@link ViewPager} object allows pages to be flipped left and right
   */
  ViewPager viewPager;

  /** The currently selected tab **/
  private int selected = 0;

  /**
   * The handle to the {@link Connection} which holds the data for the client
   * selected
   **/
  private String clientHandle = null;

  /** This instance of <code>ConnectionDetails</code> **/
  private final ConnectionDetails connectionDetails = this;

  /**
   * The instance of {@link Connection} that the <code>clientHandle</code>
   * represents
   **/
  private Connection connection = null;

  /**
   * The {@link ChangeListener} this object is using for the connection
   * updates
   **/
  private ChangeListener changeListener = null;

  /**
   * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    clientHandle = getIntent().getStringExtra("handle");

    //setContentView(R.layout.activity_connection_details);
    // Create the adapter that will return a fragment for each of the pages
    sectionsPagerAdapter = new SectionsPagerAdapter(
        getSupportFragmentManager());

    // Set up the action bar for tab navigation
    final ActionBar actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    // Create the tabs for the screen
    for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
      ActionBar.Tab tab = actionBar.newTab();
      tab.setText(sectionsPagerAdapter.getPageTitle(i));
      tab.setTabListener(this);
      actionBar.addTab(tab);
    }

    connection = Connections.getInstance(this).getConnection(clientHandle);
    changeListener = new ChangeListener();
    connection.registerChangeListener(changeListener);
  }

  @Override
  protected void onDestroy() {
    connection.removeChangeListener(null);
    super.onDestroy();
  }

  /**
   * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    int menuID;
    Integer button = null;
    boolean connected = Connections.getInstance(this)
        .getConnection(clientHandle).isConnected();
    // inflate the menu selected
    //getMenuInflater().inflate(menuID, menu);
    // add listeners
    if (button != null) {
      // add listeners
      if (!Connections.getInstance(this).getConnection(clientHandle)
          .isConnected()) {
        menu.findItem(button).setEnabled(false);
      }
    }

    return true;
  }

  /**
   * @see android.app.ActionBar.TabListener#onTabUnselected(android.app.ActionBar.Tab,
   *      android.app.FragmentTransaction)
   */
  @Override
  public void onTabUnselected(ActionBar.Tab tab,
      FragmentTransaction fragmentTransaction) {
    // Don't need to do anything when a tab is unselected
  }

  /**
   * @see android.app.ActionBar.TabListener#onTabSelected(android.app.ActionBar.Tab,
   *      android.app.FragmentTransaction)
   */
  @Override
  public void onTabSelected(ActionBar.Tab tab,
      FragmentTransaction fragmentTransaction) {
    // When the given tab is selected, switch to the corresponding page in
    // the ViewPager.
    viewPager.setCurrentItem(tab.getPosition());
    selected = tab.getPosition();
    // invalidate the options menu so it can be updated
    invalidateOptionsMenu();
    // history fragment is at position zero so get this then refresh its
    // view
  }

  /**
   * @see android.app.ActionBar.TabListener#onTabReselected(android.app.ActionBar.Tab,
   *      android.app.FragmentTransaction)
   */
  @Override
  public void onTabReselected(ActionBar.Tab tab,
      FragmentTransaction fragmentTransaction) {
    // Don't need to do anything when the tab is reselected
  }

  /**
   * Provides the Activity with the pages to display for each tab
   *
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter {

    // Stores the instances of the pages
    private ArrayList<Fragment> fragments = null;

    /**
     * Only Constructor, requires a the activity's fragment managers
     *
     * @param fragmentManager
     */
    public SectionsPagerAdapter(FragmentManager fragmentManager) {
      super(fragmentManager);
      fragments = new ArrayList<Fragment>();
      // create the history view, passes the client handle as an argument
      // through a bundle
//      Fragment fragment = new HistoryFragment();
//      Bundle args = new Bundle();
//      args.putString("handle", getIntent().getStringExtra("handle"));
//      fragment.setArguments(args);
      // add all the fragments for the display to the fragments list
//      fragments.add(fragment);
//      fragments.add(new SubscribeFragment());
//      fragments.add(new PublishFragment());

    }

    /**
     * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
     */
    @Override
    public Fragment getItem(int position) {
      return fragments.get(position);
    }

    /**
     * @see android.support.v4.view.PagerAdapter#getCount()
     */
    @Override
    public int getCount() {
      return fragments.size();
    }
  }

  /**
   * <code>ChangeListener</code> updates the UI when the {@link Connection}
   * object it is associated with updates
   *
   */
  private class ChangeListener implements PropertyChangeListener {

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
      // connection object has change refresh the UI
    }
  }

}
