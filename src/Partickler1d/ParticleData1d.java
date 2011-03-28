package Partickler1d;

import java.util.ArrayList;

public class ParticleData1d {

	//private ArrayList<Integer> aliDirection = new ArrayList<Integer>();
	private ArrayList<Double> aldVelocity = new ArrayList<Double>();
	private ArrayList<Double> aldPos0 = new ArrayList<Double>();
	private double dMaxPos0, dMinPos0;
	private double dMaxVel, dMinVel;
	
	public ParticleData1d() {
		clear();
	}

	public void clear() {
		aldVelocity = new ArrayList<Double>();
		aldPos0 = new ArrayList<Double>();
		dMaxPos0 = Integer.MIN_VALUE;
		dMinPos0 = Integer.MAX_VALUE;
		dMaxVel = Integer.MIN_VALUE;
		dMinVel = Integer.MAX_VALUE;
	}
	public double getMinPos0(){
		return dMinPos0;
	}
	public double getMaxPos0(){
		return dMaxPos0;
	}
	public double getMinVel(){
		return dMinVel;
	}
	public double getMaxVel(){
		return dMaxVel;
	}
	public int size(){
		return aldVelocity.size();
	}
	
	public double getPatIndex(int index) {
		return aldPos0.get(index);
	}
	
	public double getVatIndex(int index) {
		return aldVelocity.get(index);
	}
	
	public double[] getVarray() {
		double[] adReturn = null;
		String[] asReturn = new String[aldVelocity.size()];
		aldVelocity.toArray(asReturn);
		try {
			adReturn = AnimTool.convertStringArraytoDoubleArray(asReturn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adReturn;
	}
	
	public double[] getParray() {
		double[] adReturn = null;
		String[] asReturn = new String[aldPos0.size()];
		aldPos0.toArray(asReturn);
		try {
			adReturn = AnimTool.convertStringArraytoDoubleArray(asReturn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adReturn;
	}
	
	public boolean append(String sDirs, double[] adVels, double[] adPos0) {
		//test that the string length is equal to the length of the 2 array and nonzero
		int set_size = sDirs.length();
		if (adVels.length!=set_size || adPos0.length!=set_size) {
			AnimTool.ShowMessgaeBox("List sizes do not match");
			return false;
		}
		/*
		// test that the sDirs contains only capital Ls and capital Rs
		if(sDirs.replace("[LR]","").length()>0) {
			AnimTool.ShowMessgaeBox("List sizes do not match");
			return false;
		}
		*/
		
		//import data
		int val;
		for(int i =0; i<set_size;i++) {
			val = (sDirs.charAt(i)=='L') ? -1 : 1;
			//aliDirection.add(val);
			double velo = adVels[i] * val;
			aldVelocity.add(velo);
			aldPos0.add(adPos0[i]);
			
			if  (velo>dMaxVel) {
				dMaxVel = velo;
			}
			if  (velo<dMinVel) {
				dMinVel = velo;
			}
			if  (adPos0[i]>dMaxPos0) {
				dMaxPos0 = adPos0[i];
			}
			if  (adPos0[i]<dMinPos0) {
				dMinPos0 = adPos0[i];
			}
		}
		return true;
		
		
	}
	
}
