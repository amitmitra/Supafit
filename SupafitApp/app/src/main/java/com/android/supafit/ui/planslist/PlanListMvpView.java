package com.android.supafit.ui.planslist;

import com.android.supafit.netoperations.networkmodel.program.Program;
import com.android.supafit.ui.base.MvpView;

/**
 * Created by harsh on 2/13/16.
 */
public interface PlanListMvpView extends MvpView {

  public void startProgressBar();

  public void stopProgressBar();

  public void loadPlan1View(Program planPackage);

  public void loadPlan2View(Program planPackage);

  public void loadPlan3View(Program planPackage);

  public void goToNextScreen();

  void showError();
}
