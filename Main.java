public class Main implements CommandExecutor {
 
    private Main plugin;
 
    public Main(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("ping").setExecutor(this);
    }
 
    String ping = String.valueOf(0);
    String oldping = ping;
 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ping")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ping = String.valueOf(((CraftPlayer) p).getHandle().ping);
                oldping = ping;
                p.sendMessage("Ping "+ping);
                new BukkitRunnable() {
                    public void run() {
                        ping = String.valueOf(((CraftPlayer) p).getHandle().ping);
                        if (!oldping.equals(ping)) {
                            oldping = ping;
                            p.sendMessage("Ping "+ping);
                        }
                    }
                }.runTaskTimer(plugin, 20*2, 20*2);
                return true;
            } else {
                sender.sendMessage("nope");
                return true;
            }
        }
        return false;
    }
}
