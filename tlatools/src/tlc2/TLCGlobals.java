// Copyright (c) 2003 Compaq Corporation.  All rights reserved.
// Portions Copyright (c) 2003 Microsoft Corporation.  All rights reserved.
// Last modified on Thu 18 September 2008 at  1:49:20 PST by lamport
//      modified on Fri Mar  6 23:02:34 PST 2002 by yuanyu

package tlc2;

import tla2sany.semantic.FrontEnd;
import tlc2.tool.ModelChecker;

public class TLCGlobals {

  // The current version of TLC
  public static String versionOfTLC = "Version 2.01 of 20 February 2009";

  // The bound for set enumeration, used for pretty printing
  public static int enumBound = 2000;

  // The bound for the cardinality of a set
  public static int setBound = 1000000;

  // Number of concurrent workers
  private static int numWorkers = 1;

  public synchronized static void setNumWorkers(int n) {
    numWorkers = n;
  }

  public synchronized static int getNumWorkers() {
    return numWorkers;
  }

  public synchronized static void incNumWorkers(int n) {
    numWorkers += n;
  }

  // The main model checker object
  public static ModelChecker mainChecker = null;
  
  // Enable collecting coverage information
  public static int coverageInterval = -1;

  // Depth for depth-first iterative deepening
  public static int DFIDMax = -1;
  
  // Continue running even when invariant is violated
  public static boolean continuation = false;

  // Prints only the state difference in state traces
  public static boolean printDiffsOnly = false;

  // Suppress warnings report if true
  public static boolean warn = true;

  // The time interval to report progress
  public static final int progressInterval = 300000;

  // The time interval to checkpoint.
  public static final long chkptDuration = 1800000;
  
  // The meta data root.
  public static final String metaRoot = "states";
  public static String metaDir = null;

  // The flag to control if VIEW is applied when printing out states.
  public static boolean useView = false;

  // The flag to control if gzip is applied to Value input/output stream.
  public static boolean useGZIP = true;

  // The list of fingerprint servers.
  public static String[] fpServers = null;

  // The tool id number for TLC2.
  public static int ToolId = FrontEnd.getToolId();

}
