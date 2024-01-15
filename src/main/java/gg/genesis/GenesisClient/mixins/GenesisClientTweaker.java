// Tweaker for the client (basically lets us inject code)

package gg.genesis.GenesisClient.mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.MixinEnvironment.Side;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class GenesisClientTweaker implements ITweaker {

    private static final List<String> args = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        GenesisClientTweaker.args.addAll(args);
        if(gameDir != null) {
            GenesisClientTweaker.args.add("--gameDir");
            GenesisClientTweaker.args.add(gameDir.getAbsolutePath());
        }
        if(assetsDir != null) {
            GenesisClientTweaker.args.add("--assetsDir");
            GenesisClientTweaker.args.add(assetsDir.getAbsolutePath());
        }
        if(profile != null) {
            GenesisClientTweaker.args.add("--version");
            GenesisClientTweaker.args.add(profile);
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.GenesisClient.json");

        MixinEnvironment environment = MixinEnvironment.getDefaultEnvironment();

        if(environment.getObfuscationContext() == null) {
            environment.setObfuscationContext("notch");
        }

        environment.setSide(Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }

    @Override
    public String[] getLaunchArguments() {
        return GenesisClientTweaker.args.toArray(new String[0]);
    }

}