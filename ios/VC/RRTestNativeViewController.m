//
//  RRTestNativeViewController.m
//  RRReactNative
//
//  Created by Tommy Liu（Loftyworks Team） on 2024/5/30.
//

#import "RRTestNativeViewController.h"
#import <React/RCTRootView.h>
#import <React/RCTBundleURLProvider.h>

@interface RRTestNativeViewController ()

@end

@implementation RRTestNativeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
        
    NSURL *jsCodeLocation = [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"main" fallbackURLProvider:^NSURL *{
        return [NSURL URLWithString:@"http://10.2.97.252:8081/index.bundle?platform=ios"];
    }];
    if (jsCodeLocation == nil) {
        return;
    }
    
    RCTRootView *rootView = [[RCTRootView alloc] initWithBundleURL:jsCodeLocation
                                                        moduleName:@"TestComponent"
                                                 initialProperties:self.startParams
                                                      launchOptions: nil];
    rootView.frame = self.view.bounds;
    [self.view addSubview:rootView];
}

@end
