require_relative 'node_modules/react-native/scripts/react_native_pods'

def installReactNativePods(
    module_path: "../node_modules/react-native",
    app_path: '..'
)
    Dir.chdir(module_path)
    # flags = get_default_flags()
    use_react_native!(
      :path => "#{module_path}/node_modules/react-native"
      # An absolute path to your application root.
    )
end

                  
#    Dir.chdir("../LocalPods/RRReactNative")
#    flags = get_default_flags()
##    use_react_native!(:path => "../LocalPods/RRReactNative/node_modules/react-native")
#    use_react_native!(
#      :path => "../LocalPods/RRReactNative/node_modules/react-native",
#      # Hermes is now enabled by default. Disable by setting this flag to false.
#      :hermes_enabled => flags[:hermes_enabled],
#      :fabric_enabled => flags[:fabric_enabled],
#      # An absolute path to your application root.
#      :app_path => "#{Pod::Config.instance.installation_root}/.."
#    )
#  