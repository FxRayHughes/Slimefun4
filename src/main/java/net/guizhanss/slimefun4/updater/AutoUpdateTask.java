package net.guizhanss.slimefun4.updater;

import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.lang.reflect.Method;

/**
 * 自动更新任务.
 *
 * @author ybw0014
 */
public class AutoUpdateTask implements Runnable {

    private static final String GITHUB_USER = "StarWishsama";
    private static final String GITHUB_REPO = "Slimefun4";
    private static final String GITHUB_BRANCH_CANARY = "master";
    private static final String GITHUB_BRANCH_RELEASE = "release";

    private final Plugin plugin;
    private final File file;
    private final String version;

    @ParametersAreNonnullByDefault
    public AutoUpdateTask(Plugin plugin, File file) {
        this.plugin = plugin;
        this.file = file;
        this.version = plugin.getDescription().getVersion();
    }

    @Override
    public void run() {
        String branch = getBranch();
        if (branch == null) {
            return;
        }
        try {
            // use updater in lib plugin
            Class<?> clazz = Class.forName("net.guizhanss.guizhanlibplugin.updater.GuizhanBuildsUpdaterWrapper");
            Method updaterStart = clazz.getDeclaredMethod("start", Plugin.class, File.class, String.class, String.class, String.class, Boolean.class);
            updaterStart.invoke(null, plugin, file, GITHUB_USER, GITHUB_REPO, branch, false);
        } catch (Exception ignored) {
            // use updater in lib
            new GuizhanBuildsUpdater(plugin, file, GITHUB_USER, GITHUB_REPO, branch, false).start();
        }
    }

    @Nullable
    private String getBranch() {
        if (version.endsWith("release")) {
            return GITHUB_BRANCH_RELEASE;
        } else if (version.endsWith("canary")) {
            return GITHUB_BRANCH_CANARY;
        } else {
            return null;
        }
    }
}
