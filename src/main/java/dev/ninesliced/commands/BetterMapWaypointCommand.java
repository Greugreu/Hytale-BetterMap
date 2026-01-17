package dev.ninesliced.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class BetterMapWaypointCommand extends AbstractCommandCollection {

    @Override
    protected String generatePermissionNode() {
        return "";
    }

    public BetterMapWaypointCommand() {
        super("waypoint", "Manage map waypoints");
        this.addAliases("marker");
        this.addSubCommand(new BetterMapWaypointAddCommand());
        this.addSubCommand(new BetterMapWaypointDeleteCommand());
        this.addSubCommand(new BetterMapWaypointListCommand());
        this.addSubCommand(new BetterMapWaypointUpdateCommand());
        this.addSubCommand(new BetterMapWaypointTeleportCommand());
        this.addSubCommand(new BetterMapWaypointIdCommand());
        this.addSubCommand(new BetterMapMenuCommand());
    }
}
