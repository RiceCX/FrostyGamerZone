package dev.ricecx.frostygamerzone.core;


import dev.ricecx.frostygamerzone.api.plugins.core.LuckPermsAPI;
import dev.ricecx.frostygamerzone.bukkitapi.CorePlugin;
import dev.ricecx.frostygamerzone.api.plugins.core.FrostyGamerCoreAPI;
import dev.ricecx.frostygamerzone.bukkitapi.module.Module;
import dev.ricecx.frostygamerzone.bukkitapi.user.core.FrostUser;
import dev.ricecx.frostygamerzone.bukkitapi.user.impl.FrostUserImpl;
import dev.ricecx.frostygamerzone.bukkitapi.user.utils.UserRegister;
import dev.ricecx.frostygamerzone.core.api.FrostyCoreAPI;
import dev.ricecx.frostygamerzone.core.commands.NametagCommand;
import dev.ricecx.frostygamerzone.core.commands.NetworkCommand;
import dev.ricecx.frostygamerzone.core.commands.PingCommand;
import dev.ricecx.frostygamerzone.core.modules.Modules;
import dev.ricecx.frostygamerzone.core.modules.luckperms.LuckPermsModule;

import java.util.UUID;

public final class FrostyGamerCore extends CorePlugin implements FrostyGamerCoreAPI, UserRegister<FrostUser> {

    @Override
    public void onEnable() {
        super.onEnable();
        long startTime = System.currentTimeMillis();
        getLogger().info("Loading FrostyGamerCore!");


        Modules.LUCK_PERMS.enable();
        Modules.CHAT.enable();
        Modules.NO_BLOCK_BREAK.enable();
        Modules.NO_HUNGER.enable();
        Modules.NO_VANILLA_JOIN.enable();
        Modules.NAME_TAGS.enable();

        loadCommands();
        registerService(FrostyGamerCoreAPI.class, new FrostyCoreAPI());
        getLogger().info("Loaded FrostyGamerCore in " + (System.currentTimeMillis() - startTime) + " ms");
    }



    public void loadCommands() {
        registerCommands(
                new PingCommand(),
                new NetworkCommand(),
                new NametagCommand()
        );
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public LuckPermsAPI getLuckPermsAPI() {
        if(Module.getModule(LuckPermsModule.class).isEmpty()) return null;
        return Module.getModule(LuckPermsModule.class).get();
    }

    @Override
    public FrostUser registerUser(String name, UUID uuid) {
        return new FrostUserImpl(name, uuid);
    }
}