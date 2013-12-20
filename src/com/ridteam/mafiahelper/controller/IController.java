package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IModel;
import com.ridteam.mafiahelper.views.IView;


/** 
 * Controller interface for view.
 * 
 * @author Shurygin Denis
 *
 */
public interface IController {
	public IView getView();
	public IModel getModel();
}