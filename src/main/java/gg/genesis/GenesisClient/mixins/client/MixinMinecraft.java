// File that handles everything background-related during startup

package gg.genesis.GenesisClient.mixins.client;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    // Change title
    @Inject(method = "createDisplay", at = @At("RETURN"))
    public void createDisplay(CallbackInfo callbackInfo) {
        Display.setTitle("Genesis Client 1.8.9 | Version 0.0.1");
    }
}
