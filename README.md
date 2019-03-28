## notepad
> 一款具有图片记事，语音记事,文字记事功能的记事本

# 项目结构
> - activity 具体不同的界面
>     - ContentDetailActivity
>     - PublishActivity
>     - PublishSpeechActivity
> - adapter  创建的list适配器展示不同的item
>     - holders
>       - HolderCharPicNote
>       - HolderSpeechNote
>     - NoteContentAdapter
>     - RecyclerViewAdapter
> - base  最基本的类
>     - HolderBase
>     - HolderBaseRecycler
> - bean  记事本数据类
>     - NoteBean
> - dialog 抛出来的弹框进行删除，分享操作等
>     - BaseFileDialog
> - fragment  对应的不同界面
>     - EmptyFragment
>     - NoteContentFragment
>     - PlayREcordingDialogFragment
> - service   录音服务的提供
>     - RecordingService
> - util  该文件主要放置一些处理图片，数据库，以及权限授取等工具类操作
> - NoteApplication
> - NoteMainActivity

## 反馈建议
> 如有问题请提交issue

## 感谢
本项目引用了以下大神的开源库，感谢！
- https://github.com/KDF5000/RichEditText
- https://github.com/zhihu/Matisse