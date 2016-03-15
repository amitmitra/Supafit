package com.android.supafit.ui.planslist;

import com.android.supafit.model.networkmodel.PlanPackage;
import com.android.supafit.ui.base.MvpView;

/**
 * Created by harsh on 2/13/16.
 */
public interface PlanListMvpView extends MvpView {

  public void startProgressBar();

  public void stopProgressBar();

  public void loadPlan1View(PlanPackage planPackage);

  public void loadPlan2View(PlanPackage planPackage);

  public void loadPlan3View(PlanPackage planPackage);

  public void goToNextScreen();

  void showError();
}
