package ParticklerPolar;

import java.util.ArrayList;

public class ParticleDataPolar {

	private ArrayList<Double> aldDirection = new ArrayList<Double>();
	private ArrayList<Double> aldVelocity = new ArrayList<Double>();
	private ArrayList<Double> aldPos0_direction = new ArrayList<Double>();
	private ArrayList<Double> aldPos0_distance = new ArrayList<Double>();
	private double dMaxPos0;
	private double dMaxVel, dMinVel;
	
	public ParticleDataPolar() {
		clear();
	}

	public void clear() {
		aldDirection = new ArrayList<Double>();
		aldVelocity = new ArrayList<Double>();
		aldPos0_direction = new ArrayList<Double>();
		aldPos0_distance = new ArrayList<Double>();
		dMaxPos0 = Integer.MIN_VALUE;
		//dMinPos0 = Integer.MAX_VALUE;
		dMaxVel = Integer.MIN_VALUE;
		dMinVel = Integer.MAX_VALUE;
	}
	
	//in a polar system minimum position isn't really interesting
	/*
	public double getMinPos0(){
		return dMinPos0;
	}
	*/
	
	// for our polar example this function will return the max starting distance from the origin(0,0)
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
	
	public double getPdirectionatIndex(int index) {
		return aldPos0_direction.get(index);
	}

	public double getPdistanceatIndex(int index) {
		return aldPos0_distance.get(index);
	}
	
	public double getVatIndex(int index) {
		return aldVelocity.get(index);
	}
	
	public double getDirectionatIndex(int index) {
		return aldDirection.get(index);
	}
	
	/*
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
	*/
	
	public boolean append(double[] adDirs, double[] adVels, double[] adPos0_dir, double[] adPos0_dis) {
		//test that the string length is equal to the length of the 2 array and nonzero
		int set_size = adDirs.length;
		if (adVels.length!=set_size || adPos0_dir.length!=set_size || adPos0_dis.length!=set_size) {
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
		for(int i =0; i<set_size;i++) {
			double velo =  adVels[i];
			aldDirection.add(Math.toRadians(adDirs[i]));
			aldVelocity.add(velo );
			aldPos0_direction.add(Math.toRadians(adPos0_dir[i]));
			aldPos0_distance.add(adPos0_dis[i]);
			
			if  (velo>dMaxVel) {
				dMaxVel = velo;
			}
			if  (velo<dMinVel) {
				dMinVel = velo;
			}
			if  (adPos0_dis[i]>dMaxPos0) {
				dMaxPos0 = adPos0_dis[i];
			}
		}
		return true;
		
		
	}
	
}
