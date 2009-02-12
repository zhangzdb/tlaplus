package org.lamport.tla.toolbox.util.pref;

/**
 *
 * @author zambrovski
 */
public interface IPreferenceConstants
{

    public static final String P_PROJECT_ROOT_FILE      = "ProjectRootFile";
    public static final String P_PROJECT_OPENED_MODULES = "ProjectOpenedModules";
    public static final String DEFAULT_NOT_SET                = "not set";
    
    public static final String P_PARSER_POPUP_ERRORS        = "parserPopupErrors";
    
    public static final String I_PARSE_FILES_ON_MODIFY      = "autoParseTopModules";
    public static final String I_PARSE_MODULE_ON_MODIFY     = "autoParseModule";
    public static final String I_PARSE_SPEC_ON_MODIFY       = "autoParseSpec";
    public static final String I_RESTORE_LAST_SPEC          = "restoreLastSpec";
    public static final String I_SPEC_LOADED                = "specLoadedName";

}
